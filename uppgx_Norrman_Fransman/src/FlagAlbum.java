import java.util.ArrayList;

public class FlagAlbum extends AbstractSearchAlbum {

	public FlagAlbum (Album parent, String name) { //skapar sub album
		this.setParent(parent);
		this.setName(name);
	}
	
	
	public ArrayList<SoundClip> getSoundClipsRec() {

		clearClips();
		
		return getSoundClips();
	}


	protected void clearClips() {
		for (int i = 0; i < getSoundClips().size(); i++) {
			if(!getSoundClip(i).flagged) {
				getSoundClips().remove(i);
				clearClips();
				break;
			}
		}
	}
	
}
