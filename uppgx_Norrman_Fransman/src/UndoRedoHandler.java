

import java.util.LinkedList;
import java.util.List;

public class UndoRedoHandler {
	
	private LinkedList<Album> undoRedo;
	private static final int undoDepth = 20;
	private int cur = 0; //"pointer"
	
	
	public UndoRedoHandler(Album root) {
		undoRedo = new LinkedList<Album>();
		undoRedo.add(recursiveChange(root,null));
	}
	
	
	public void change(Album root) {
		
		Album newRoot = recursiveChange(root, null);
		undoRedo.add(cur, newRoot);
		cur++;
		if( undoRedo.size() > undoDepth) {
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


	public Album undo() {
		
		Album newRoot;
		
		if(cur>0) {
			cur--;
			return undoRedo.get(cur);
			
		}
		else {
			return null;	
		}
	}
	
	
	

}
