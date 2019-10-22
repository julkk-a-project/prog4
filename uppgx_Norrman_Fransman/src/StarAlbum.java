import java.util.ArrayList;

public class StarAlbum extends AbstractSearchAlbum {

	public StarAlbum (Album parent, String name) { //skapar sub album
		this.setParent(parent);
		this.setName(name);
	}

	public ArrayList<SoundClip> getSoundClipsRec() {
		
		clearClips();
		
		return getSoundClips();
	}

	

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
