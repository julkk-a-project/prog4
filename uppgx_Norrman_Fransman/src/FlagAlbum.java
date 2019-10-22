import java.util.ArrayList;

public class FlagAlbum extends AbstractSearchAlbum {

	public FlagAlbum (Album parent, String name) { //skapar sub album
		this.setParent(parent);
		this.setName(name);
	}
	
	
	@Override
	public ArrayList<SoundClip> getSoundClipsRec() {
		return getSoundClips();
	}

	
}
