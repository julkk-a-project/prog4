
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

	private StarAlbum starAlbum;
	private FlagAlbum flagAlbum;
	
	private Album selectedAlbum;
	private AbstractAlbum doubleSelectedAlbum;
	private JTree albumTree;
	private Album newestAlbum;
	
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	
	public MusicOrganizerController() {
			
		root = new Album("All Sound Clips");
		
		doubleSelectedAlbum = root;

		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		//adds search albums to root.
		addSearchAlbums();
		
		albumTree = view.getAlbumTree();
		
		
		
		//undoRedoHandler = new UndoRedoHandler(root, this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	
	/**
	 * call only once in constructor plox
	 */
	private void addSearchAlbums() {
		

		setSelected(root);
		

		starAlbum = new StarAlbum(root, "Favourite Albums");
		flagAlbum = new FlagAlbum(root, "Flagged Albums");
		
		view.onAlbumAdded(starAlbum);
		view.onAlbumAdded(flagAlbum);
		
		
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
		
		newestAlbum = new Album((Album) selectedAlbum, name);
		x.setAlbum(newestAlbum);
		x.setAlbumParent(newestAlbum.getParent());
		x.setSoundClips(newestAlbum.getSoundClips());
		view.onAlbumAdded(newestAlbum);
		//undoStack.peek().getAlbum().setParent(newestAlbum.getParent());
		redoStack.clear();
		 
	}
	
	public void addNewAlbum(Album x){
		

		ArrayList<Album> list = UndoRedoHandler.getAllChildren(x);

		for(int i = 0; i < list.size(); i++) {
			Album current = list.get(i);
			view.onAlbumAdded(current);
		}	
		
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void removeAlbum(RemoveAlbum x){ 

		if(selectedAlbum.getParent() != null) {

			x.setAlbum((Album) selectedAlbum);
			x.setAlbumParent(selectedAlbum.getParent());
			x.setSubAlbums(((Album) selectedAlbum).getSubAlbums());
			x.setSoundClips(selectedAlbum.getSoundClips());
			view.onAlbumRemoved((Album) selectedAlbum);
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
		
		String directory = JOptionPane.showInputDialog("where is ur file??? please define the path :)",
				"sample-sound\\"); //<-- default path for testing pourposes!
		
		
		//to prevent adding soundclip when nothing is selected.
		if (selectedAlbum != null) {
			Set<SoundClip> clips = loadSoundClips(directory);
			
			ArrayList<SoundClip> clipsList = new ArrayList<>();
			clipsList.addAll(clips);
			
			selectedAlbum.addSoundClips(clips);
			
			
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
	public void removeSoundClips(RemoveSoundClips x){
		
		try {
			if(!view.getSelectedSoundClips().equals(null)) {

				x.setParent((Album) doubleSelectedAlbum);
				x.setSoundClips((ArrayList<SoundClip>) view.getSelectedSoundClips());
				
				doubleSelectedAlbum.removeSoundClips(view.getSelectedSoundClips());
				
				view.onClipsUpdated();
						
			}			
		}catch (ClassCastException e) {
			//typecast pattern
		}

		
	}
	public void removeSoundClips(ArrayList<SoundClip> x, Album parent){


		parent.removeSoundClips(x);

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
	
	public AbstractAlbum getDoubleSelectedAlbum() {
		
		
		//Typecast pattern in use!
		try {
			FlagAlbum a = (FlagAlbum) doubleSelectedAlbum;
			a.clearClips();
		}catch (ClassCastException e) {
			try {
				StarAlbum a = (StarAlbum) doubleSelectedAlbum;
				a.clearClips();
			}catch (ClassCastException f) {
				
			}			
		}
		//end of typecast pattern!
		
		
		return doubleSelectedAlbum;
	}
	
	/**
	 * autogenerated :)
	 * @param doubleSelectedAlbum
	 */
	public void setDoubleSelectedAlbum(AbstractAlbum doubleSelectedAlbum) {
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

	

//	public void removeSoundClips(RemoveSoundClips x) {
//		
//		
//		
//		
//		redoStack.clear();
//		
//	}

	public void addSoundClips(ArrayList<SoundClip> addedSoundClips, Album parent) {

		//ArrayList<SoundClip> newClipId =  
		parent.addSoundClips(addedSoundClips);

		view.onClipsUpdated();
		
		//return newClipId;
	}

	@Override
	public void removeSoundClips() {
		// TODO Auto-generated method stub
		
	}
	
	//Lucas's getters
	public StarAlbum getStarAlbum(){
		return starAlbum;
	}
	public FlagAlbum getFlagAlbum(){
		return flagAlbum;
	}
	
}

	

	
	
