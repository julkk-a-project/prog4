
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
	private Album removedAlbum;
	private Album removedAlbumParent;
	
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
		//view.getManager().clearRedoStack();
		
		newestAlbum = new Album(selectedAlbum, name);
		x.setAlbum(newestAlbum);
		view.onAlbumAdded(newestAlbum);
		
		redoStack.clear();
		 
		//undoRedoHandler.change(root);
		
	}
	
	public void addNewAlbum(Album x){
		
		view.onAlbumAdded(newestAlbum);
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void removeAlbum(RemoveAlbum x){ 

		if(selectedAlbum.getParent() != null) {

			x.setAlbum(selectedAlbum);
			view.onAlbumRemoved(selectedAlbum);
			//removedAlbum = selectedAlbum;
			//removedAlbumParent = removedAlbum.getParent();
		}
		//undoRedoHandler.change(root);
	}
	
	public void removeAlbum(Album x){ 
			
		view.onAlbumRemoved(x);
		
	}
	
		
		
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){
		
		String directory = JOptionPane.showInputDialog("where is ur file??? please define the path :)");
//				"C:\\Users\\julkk\\git\\prog4\\uppgx_Norrman_Fransman\\sample-sound\\punk.wav"); //<-- default path for testing pourposes!
		
		
		//to prevent adding soundclip when nothing is selected.
		if (selectedAlbum != null) {
			selectedAlbum.addSoundClips(loadSoundClips(directory));

			//undoRedoHandler.change(root);
			
			view.onClipsUpdated();
		}
		
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){
		
		
		if(view.getSelectedSoundClips().equals(null)) {

			selectedAlbum.removeSoundClips(view.getSelectedSoundClips());
			
		//	undoRedoHandler.change(root);
			
			view.onClipsUpdated();
					
		}
		
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
	

	/**
	 * LEGACY CODE
	 */
//	public Album findAlbum(ArrayList<Album> albums, String name){ //startar från roten och går neråt rekursivt tills rätt album hittas
//			
//		//Precondition
//		assert (root != null): "No albums available";
//		
//		try {
//		
//			for(int i = 0; i < albums.size(); i++) {
//			
//			if(albums.get(i).getName().equals(name))
//				return albums.get(i);
//			
//		}
//		
//		for(int i = 0; i < albums.size(); i++) {
//			
//			if(albums.get(i).getSubAlbums().size() != 0)
//				return findAlbum(albums.get(i).getSubAlbums(), name);
//			
//		}
//		
//		} catch(Exception e) {
//			
//			System.out.println(e);
//		
//		}
//		
//		return root;
//		
//		
//	}
	

	
	/**
	 *Söker föräldern till det albummet du vill ta bort och 
	 *callar metoden removeSubAlbum på det albummet.
	 *LEGACY CODE
	 */
	
//	public void removeAlbum(String albumName) {
//		
//		//Preconditions
//		assert (root != null): "No albums available";
//		assert (!albumName.equals(root.getName())): "Root cannot be removed";
//		
//		Album albumToBeRemoved = findAlbum(root.getSubAlbums(), albumName);
//		
//		albumToBeRemoved.getParent().removeSubAlbum(albumToBeRemoved);
//		
//		while (albumToBeRemoved.getSubAlbums() == null) {
//			
//			for(int i = 0; i < albumToBeRemoved.getSubAlbums().size(); i++) {
//				
//				//Postcondition
//				assert(albumToBeRemoved.getSubAlbums().get(i).getParent().removeSubAlbum(albumToBeRemoved.getSubAlbums().get(i))): "No album removed";
//				
//			}
//			
//		}
//		
//		
//		
//	}	
	
	
	
	
	
	//some setters and getters for fields in class.
	
	
	
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
	
	//public void undo() {

		//Album newRoot = undoRedoHandler.undo();

		//root = newRoot;
		//System.out.println("hello");


	//}

	public void redo() {
		// TODO Auto-generated method stub

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
	}

	

	
	

//	public void redo() {
//		
//		//try {
//			if(!view.getManager().getRedoStack().isEmpty())
//				redoAlbum((Album)view.getManager().undo());
//			
//			redo = false;
//		//} catch(Exception e) {
//			//System.out.println(e);
//		//}
//	}

//	private void redoAlbum(Album x) {
//		
//		//assert (!(x.equals(null)));
//		redo = true;
//		view.onAlbumAdded(x);
//		
//		
//	}

//	public void undo() {
//		
//		if(!view.getManager().getUndoStack().isEmpty()) {
//			
//			Album x = (Album)view.findNode(view.getManager().undo().getName()).getUserObject();
//			view.getManager().addToRedo(x);
//			undoAlbum(x);
//				
//	}
//	}
//	public void undoAlbum(Album x){ 
//		
//		if(x.getParent() != null) {
//			removedAlbum = x;
//			removedAlbumParent = removedAlbum.getParent();
//			view.onAlbumRemoved(x);
//
//		}
//		
//}
//	
//	public boolean isRedo() {
//		return redo;
//	}
//	
//	public Album getRemovedAlbumParent() {
//		return removedAlbumParent;
//	}
	

