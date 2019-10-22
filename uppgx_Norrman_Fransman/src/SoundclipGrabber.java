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
	
	public void register(Soundclipsobserver newObserver) { //Lägg till flear observers
		
		observers.add(newObserver);
		
	}

	public void unregister(Soundclipsobserver deleteObserver) { //Ta bort observers
		
		int index = observers.indexOf(deleteObserver);
		
		observers.remove(index);
		
	}

	public void notifyObserver(StarAlbum addStarSoundsHere, FlagAlbum addFlagSoundsHere) {
		
		for(Soundclipsobserver observer : observers) {
			
			observer.update(starSoundclips, flaggedSoundclips, addStarSoundsHere, addFlagSoundsHere);
			
		}
		
	}
	
	
	public void setSoundClips(ArrayList<SoundClip> newStarSoundclips, ArrayList<SoundClip> newFlaggedSoundclips, StarAlbum addStarSoundsHere, FlagAlbum addFlagSoundsHere) {
		
		if(!newStarSoundclips.isEmpty()) {
			
			for(SoundClip clip: newStarSoundclips) { //highly rated clips läggs in hit
				this.starSoundclips.add(clip);
			}
			
		} else {
			
			for(SoundClip clip: newFlaggedSoundclips) { //flagged clips läggs in hit
				this.flaggedSoundclips.add(clip);
			}
			
		}

		notifyObserver(addStarSoundsHere, addFlagSoundsHere); //updaterar
		starSoundclips.clear(); //tömmer listorna
		flaggedSoundclips.clear();
		
	}
	
}
