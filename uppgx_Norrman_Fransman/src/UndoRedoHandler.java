

import java.util.LinkedList;
import java.util.List;

public class UndoRedoHandler {
	
	private LinkedList<Album> undoRedo;
	private static final int undoDepth = 20;
	
	
	public UndoRedoHandler(Album root) {
		undoRedo = new LinkedList<Album>();
		undoRedo.add(root);
	}
	
	
	public void change(Album root) {
		
		Album newRoot = recursiveChange(root, null);
		undoRedo.add(newRoot);
		if( undoRedo.size() > 20) {
			undoRedo.remove(0); //should work according to my hobby testing i did this weekend.
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
	
	
	

}
