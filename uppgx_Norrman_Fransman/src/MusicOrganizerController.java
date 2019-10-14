
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTree;

public class MusicOrganizerController implements Actions {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album selectedAlbum;
	private Album doubleSelectedAlbum;
	private JTree albumTree;
	private Album newestAlbum;
	
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	//private UndoRedoHandler undoRedoHandler;
	private boolean redo = false;
	
	
	//private Album previousAlbum; 
	
	
	public MusicOrganizerController() {
			
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		albumTree = view.getAlbumTree();
		
		//undoRedoHandler = new UndoRedoHandler(root, this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		// TODO: Add the loaded sound clips to the root album

		return clips;
	}
	
	
	 
	
	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(AddAlbum x){ 
		
		assert (!(selectedAlbum.equals(null)));
		
		String name = view.promptForAlbumName();
		
		newestAlbum = new Album(selectedAlbum, name);
		x.setAlbum(newestAlbum);
		x.setAlbumParent(newestAlbum.getParent());
		x.setSoundClips(newestAlbum.getSoundClipsList());
		view.onAlbumAdded(newestAlbum);
		//undoStack.peek().getAlbum().setParent(newestAlbum.getParent());
		redoStack.clear();
		 
	}
	
	public void addNewAlbum(Album x){
		

		ArrayList<Album> list = UndoRedoHandler.getAllChildren(x);

		System.out.println("sysout list size: "+list.size());
		
		for(int i = 0; i < list.size(); i++) {
			Album current = list.get(i);
			System.out.println("remove album x: "+current.getName());
			view.onAlbumAdded(current);
		}	
		
		//view.onAlbumAdded(x);
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void removeAlbum(RemoveAlbum x){ 

		if(selectedAlbum.getParent() != null) {

			x.setAlbum(selectedAlbum);
			x.setAlbumParent(selectedAlbum.getParent());
			x.setSubAlbums(selectedAlbum.getSubAlbums());
			x.setSoundClips(selectedAlbum.getSoundClipsList());
			view.onAlbumRemoved(selectedAlbum);
			redoStack.clear();
			
		}
		
	}
	
	public void removeAlbum(Album x){ 
			
		
		

		
		view.onAlbumRemoved(x);
		
	}
	
		
		
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(AddSoundClips x){
		
		String directory = JOptionPane.showInputDialog("where is ur file??? please define the path :)");
//				"C:\\Users\\julkk\\git\\prog4\\uppgx_Norrman_Fransman\\sample-sound\\punk.wav"); //<-- default path for testing pourposes!
		
		
		//to prevent adding soundclip when nothing is selected.
		if (selectedAlbum != null) {
			Set<SoundClip> clips = loadSoundClips(directory);
			
			ArrayList<SoundClip> clipsList = new ArrayList<>();
			clipsList.addAll(clips);
			
			selectedAlbum.addSoundClips(clips);
			
			x.setSoundClips(clipsList);
			x.setParent(selectedAlbum);
			
			
			//undoRedoHandler.change(root);
			
			view.onClipsUpdated();
			redoStack.clear();
		}
		
	}
	
	public void addSoundClips() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){
		
		
		if(!view.getSelectedSoundClips().equals(null)) {

			selectedAlbum.removeSoundClips(view.getSelectedSoundClips());
			
			view.onClipsUpdated();
					
		}
		
	}
	public void removeSoundClips(ArrayList<SoundClip> x, Album parent){


		parent.removeSoundClips(view.getSelectedSoundClips());

		view.onClipsUpdated();



	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}
	
	public Album getNewestAlbum() {
		return newestAlbum;
	}
	

	//comment below helps unwanted comments to show in setter from legacy code
	/**
	 * setter
	 * @param album
	 */
	public void setSelected(Album album) {
		
		if (album != null) {
			selectedAlbum = album;
		}

	}	
	public Album getSelected() {
		
		return selectedAlbum;

	}
	
	public Album getDoubleSelectedAlbum() {
		return doubleSelectedAlbum;
	}
	
	/**
	 * autogenerated :)
	 * @param doubleSelectedAlbum
	 */
	public void setDoubleSelectedAlbum(Album doubleSelectedAlbum) {
		this.doubleSelectedAlbum = doubleSelectedAlbum;
		view.onClipsUpdated();
	}
	
	
	public Stack<Command> getUndoStack(){
		return undoStack;
	}
	
	public Stack<Command> getRedoStack(){
		return redoStack;
	}
	
	public MusicOrganizerWindow getView() {
		return view;
	}
	
	public void setButtons() {
		
		if(redoStack.empty())
			view.getButtons().getRedoButton().setVisible(false);
		else
			view.getButtons().getRedoButton().setVisible(true);
		
		if(undoStack.empty())
			view.getButtons().getUndoButton().setVisible(false);
		else
			view.getButtons().getUndoButton().setVisible(true);
		
	}

	

	public void removeSoundClips(RemoveSoundClips removeSoundClips) {
		// TODO IMPLEMENT PRONTO
		
		
		redoStack.clear();
		
	}
	}

	

	
	
