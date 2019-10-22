import java.util.ArrayList;

public interface Observer {
	
	public void update(ArrayList<SoundClip> starsoundclips, ArrayList<SoundClip> flaggedSoundclips, StarAlbum addStarSoundsHere, FlagAlbum addFlagSoundsHere);
	
}
