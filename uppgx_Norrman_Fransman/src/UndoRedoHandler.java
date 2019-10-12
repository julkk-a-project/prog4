

import java.util.Stack;
import java.util.List;

public class UndoRedoHandler {
	
	
	private Stack<Album> undoStack;
	private Stack<Album> redoStack;
	private MusicOrganizerController controller;
	private static final int undoDepth = 20;
	
	
	public UndoRedoHandler(Album root, MusicOrganizerController controller) {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		this.controller = controller;
		undoStack.push(root);
	}
	
	
	public void change(Album root) {
		
		Album newRoot = recursiveChange(root, null);
		undoStack.push(newRoot);
		if( undoStack.size() > undoDepth) {
			undoStack.remove(undoStack.size() - 1); //should work according to my hobby testing i did this weekend.
		}
		
	}
	
	
	/**
	 * recursive method to recreate a copy of root.
	 * @param curAlbum
	 * @param parAlbum
	 * @return
	 */
	private Album recursiveChange(Album curAlbum, Album parAlbum) {
		
		Album newAlbum;
		List<Album> curSubAlbums = curAlbum.getSubAlbums();
		List<SoundClip> curSoundClips = curAlbum.getSoundClips();
		
		newAlbum = new Album(parAlbum, curAlbum.getName());

		
		//adds all soundClips
		for(int i = 0; i < curSoundClips.size(); i++) {
			newAlbum.addSoundClip(curSoundClips.get(i));
		}
		
		//adds all subalbums
		for(int i = 0; i < curSubAlbums.size(); i++) {
			newAlbum.addSubAlbum(recursiveChange(curSubAlbums.get(i), newAlbum));
		}
		
		
		
		return newAlbum;
		
	}
	
	public Album undo() {
		
		if(!undoStack.isEmpty())	
			return undoStack.pop();
		
		return controller.getRootAlbum();
			
		}
	
	

}
