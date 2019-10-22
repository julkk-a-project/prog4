import java.util.ArrayList;

public class Soundclipsobserver implements Observer {

	ArrayList<SoundClip> starSoundclips;
	ArrayList<SoundClip> flaggedSoundclips;
	
	private int observerIDTracker = 0;
	
	private int observerID;
	
	private Subject soundclipGrabber;
	
	
	public Soundclipsobserver(Subject soundclipGrabber) {
		
		this.soundclipGrabber = soundclipGrabber;
		
		this.observerID = ++observerIDTracker;
		
		System.out.println("New Flag Observer " + this.observerID);
		
		soundclipGrabber.register(this);
		
	}
	
	
	public void update(ArrayList<SoundClip> starSoundclips, ArrayList<SoundClip> flaggedSoundclips) {
		
		this.starSoundclips = starSoundclips;
		this.flaggedSoundclips = flaggedSoundclips;
		
		System.out.println("Soundclips updated");

	}

}
