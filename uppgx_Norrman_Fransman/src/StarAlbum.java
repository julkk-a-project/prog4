import java.util.ArrayList;

public class StarAlbum extends AbstractSearchAlbum {

	public StarAlbum (Album parent, String name) { //skapar sub album
		this.setParent(parent);
		this.setName(name);
	}

	@Override
	public ArrayList<SoundClip> getSoundClipsRec() {
		// TODO Auto-generated method stub
		return null;
	}

}
