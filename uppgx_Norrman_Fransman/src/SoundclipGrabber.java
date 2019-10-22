import java.util.ArrayList;

public class SoundclipGrabber implements Subject {
	
	private ArrayList<Soundclipsobserver> observers;
	
	private ArrayList<SoundClip> starSoundclips;
	private ArrayList<SoundClip> flaggedSoundclips;
	
	
	public SoundclipGrabber() {
		
		observers = new ArrayList<Soundclipsobserver>();
		starSoundclips = new ArrayList<SoundClip>();
		flaggedSoundclips = new ArrayList<SoundClip>();
	}
	
	public void register(Soundclipsobserver newObserver) {
		
		observers.add(newObserver);
		
	}

	public void unregister(Soundclipsobserver deleteObserver) {
		
		int index = observers.indexOf(deleteObserver);
		
		System.out.println("Observer " + (index + 1) + " deleted.");
		
		observers.remove(index);
		
	}

	public void notifyObserver(StarAlbum x, FlagAlbum y) {
		
		for(Soundclipsobserver observer : observers) {
			
			observer.update(starSoundclips, flaggedSoundclips, x, y);
			
		}
		
	}
	
	
	public void setSoundClips(ArrayList<SoundClip> newStarSoundclips, ArrayList<SoundClip> newFlaggedSoundclips, StarAlbum x, FlagAlbum y) {
		
		if(!newStarSoundclips.isEmpty()) {
			
			for(SoundClip clip: newStarSoundclips) {
				this.starSoundclips.add(clip);
			}
			
		} else {
			
			for(SoundClip clip: newFlaggedSoundclips) {
				this.flaggedSoundclips.add(clip);
			}
			
		}

		notifyObserver(x, y);
		starSoundclips.clear();
		flaggedSoundclips.clear();
		
	}
	
}
