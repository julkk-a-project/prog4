import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album selectedAlbum;
	private Album doubleSelectedAlbum;
	private SoundClip selectedSoundClip;
	
	public MusicOrganizerController() {
		
		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
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
	public void addNewAlbum(){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		
		assert (!(selectedAlbum.equals(null)));
		
		
		
		String name = view.promptForAlbumName();
		
		
		view.onAlbumAdded(new Album(selectedAlbum, name));
		 
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void removeAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here

		
		if(selectedAlbum.getParent() != null) {
			view.onAlbumRemoved(selectedAlbum);			
		}
		
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){
		
		String directory = JOptionPane.showInputDialog("where is ur file??? please define the path :)",
				"C:\\Users\\julkk\\git\\prog4\\uppgx_Norrman_Fransman\\sample-sound\\punk.wav"); //<-- default path for testing pourposes!
		
		
		System.out.println(directory);
		
		
		//to prevent adding soundclip when nothing is selected.
		if (selectedAlbum != null) {

			//selectedAlbum.addSoundClips(new File(directory));

			selectedAlbum.addSoundClips(loadSoundClips(directory));
		}
		
		

		System.out.println(directory);
		
		view.onClipsUpdated();
		
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		
		
		selectedAlbum.removeSoundClips(view.getSelectedSoundClips());
		
		//selectedSoundClips.removeSoundClip(selectedSoundClip);
		//view.getSelectedSoundClips().clear();
		
		
		view.onClipsUpdated();
		
		
//		List<SoundClip> l = view.getSelectedSoundClips();
//		for(int i=0;i<l.size();i++)
//			
//			l.remove(i);
		
		
		
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
	
	
	//TODO: FIX
	
	
	



	
	
	
	
	
	
	
	public Album findAlbum(ArrayList<Album> albums, String name){ //startar från roten och går neråt rekursivt tills rätt album hittas
			
		//Precondition
		assert (root != null): "No albums available";
		
		try {
		
			for(int i = 0; i < albums.size(); i++) {
			
			if(albums.get(i).getName().equals(name))
				return albums.get(i);
			
		}
		
		for(int i = 0; i < albums.size(); i++) {
			
			if(albums.get(i).getSubAlbums().size() != 0)
				return findAlbum(albums.get(i).getSubAlbums(), name);
			
		}
		
		} catch(Exception e) {
			
			System.out.println(e);
		
		}
		
		return root;
		
		
	}
	
	/*
	 *söker albummet du vill lägga till ett subalbum på och 
	 *callar metoden addSubAlbum() på det albummet.
	 */
	
	public void addAlbum(String parentAlbumName, String newAlbumName) { 
		
		//Precondition
		assert(root != null): "No parent available";
		
		Album parent;
		
		if (parentAlbumName.equals(root.getName()))
			parent = root;
		else
			parent = findAlbum(root.getSubAlbums(), parentAlbumName);
		
		//Postcondition
		assert(parent.addSubAlbum(new Album(parent, newAlbumName))):"No album added";
		
	}
	
	
	/*
	 *Söker föräldern till det albummet du vill ta bort och 
	 *callar metoden removeSubAlbum på det albummet.
	 */
	
	public void removeAlbum(String albumName) {
		
		//Preconditions
		assert (root != null): "No albums available";
		assert (!albumName.equals(root.getName())): "Root cannot be removed";
		
		Album albumToBeRemoved = findAlbum(root.getSubAlbums(), albumName);
		
		albumToBeRemoved.getParent().removeSubAlbum(albumToBeRemoved);
		
		while (albumToBeRemoved.getSubAlbums() == null) {
			
			for(int i = 0; i < albumToBeRemoved.getSubAlbums().size(); i++) {
				
				//Postcondition
				assert(albumToBeRemoved.getSubAlbums().get(i).getParent().removeSubAlbum(albumToBeRemoved.getSubAlbums().get(i))): "No album removed";
				
			}
			
		}
		
		
		
	}	
	
	
	
	public void setSelected(Album album) {
		
		if (album != null) {
			selectedAlbum = album;
		}

	}	
	public Album getSelected() {
		
		return selectedAlbum;

	}
	
	public void setSelectedSoundClip(SoundClip soundClip) {
		
		if (soundClip != null) {
			selectedSoundClip = soundClip;
		}

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
	



	
	
	
	
	
}
