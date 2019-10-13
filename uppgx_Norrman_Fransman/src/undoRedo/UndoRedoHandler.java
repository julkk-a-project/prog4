package undoRedo;


import java.util.List;
import java.util.Stack;

import musicPlayer.Album;
import musicPlayer.MusicOrganizerController;
import musicPlayer.SoundClip;

public class UndoRedoHandler {
	
	
	private Stack<AbstractChange> undoStack;
	private Stack<AbstractChange> redoStack;
	private MusicOrganizerController controller;
	private static final int undoDepth = 20;
	
	
	public UndoRedoHandler(MusicOrganizerController controller) {
		undoStack = new Stack<>();
		redoStack = new Stack<>();
		this.controller = controller;
	}
	

	public void change(AbstractChange change) {
		
		//Album newRoot = recursiveChange(root, null);
		undoStack.push(change);
		redoStack.clear();
		if( undoStack.size() > undoDepth) {
			undoStack.remove(undoStack.size() - 1); //should work according to my hobby testing i did this weekend.
		}
		
	}
	public void redoChange(AbstractChange change) {
		
		//Album newRoot = recursiveChange(root, null);
		undoStack.push(change);
		if( undoStack.size() > undoDepth) {
			undoStack.remove(undoStack.size() - 1); //should work according to my hobby testing i did this weekend.
		}
		
	}
	


	
	public void undo() {
		
		AbstractChange change = null;
		if(!undoStack.isEmpty())
			change = undoStack.pop();
			change.undo();
			redoStack.push(change);
		}
	
	public void redo() {
		System.out.println("bob");
		AbstractChange change = null;
		if(!redoStack.isEmpty()) {
			change = redoStack.pop();
			change.redo();
			undoStack.push(change);
		}
	}
	
	
	//LEGACY CODE
	
	/**
	 * recursive method to recreate a copy of root.
	 * @param curAlbum
	 * @param parAlbum
	 * @return
	 */
	protected static Album recursiveChange(Album curAlbum, Album parAlbum) {
		
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
			System.out.println("stuff "+newAlbum.getName());
			newAlbum.addSubAlbum(recursiveChange(curSubAlbums.get(i), newAlbum));
		}
		
		
		
		return newAlbum;
		
	}

}
