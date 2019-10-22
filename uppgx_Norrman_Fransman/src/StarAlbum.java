import java.util.ArrayList;

public class StarAlbum extends AbstractSearchAlbum {

	public StarAlbum (Album parent, String name) { //skapar sub album
		this.setParent(parent);
		this.setName(name);
	}

	
	//doesn't have subAlbums so only gets own clips
	public ArrayList<SoundClip> getSoundClipsRec() {
		
		clearClips();
		
		return getSoundClips();
	}

	
	/**
	 * finds all clips that are not supposed to be here.
	 */
	protected void clearClips() {
		for (int i = 0; i < getSoundClips().size(); i++) {
			if(getSoundClip(i).getRating() < 4) {
				getSoundClips().remove(i);
				clearClips();
				break;
			}
		}
	}

}
