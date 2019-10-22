import java.util.ArrayList;

public class Soundclipsobserver implements Observer {

	ArrayList<SoundClip> starSoundclips;
	ArrayList<SoundClip> flaggedSoundclips;
	
	//private int observerIDTracker = 0;
	
	//private int observerID;
	
	private Subject soundclipGrabber;
	
	
	public Soundclipsobserver(Subject soundclipGrabber) {
		
		this.soundclipGrabber = soundclipGrabber;
		
		starSoundclips = new ArrayList<SoundClip>();
		flaggedSoundclips = new ArrayList<SoundClip>();
		
		//this.observerID = ++observerIDTracker;
		
		System.out.println("New Observer Created");
		
		soundclipGrabber.register(this);
		
	}
	
	
	public void update(ArrayList<SoundClip> starSoundclips, ArrayList<SoundClip> flaggedSoundclips, StarAlbum addStarSoundsHere, FlagAlbum addFlagSoundsHere) {
		
		if(!starSoundclips.isEmpty()) {

			for(SoundClip clip: starSoundclips) {
				if(!clip.toBeremoved()) {
					this.starSoundclips.add(clip);
				} else {	
					this.starSoundclips.remove(clip);	
				}
				
			}
		} 
		if(!flaggedSoundclips.isEmpty()){

			for(SoundClip clip: flaggedSoundclips) {
				if(!clip.toBeremoved()) {
					this.flaggedSoundclips.add(clip);
				} else {	
					this.flaggedSoundclips.remove(clip);	
				}
			}
		}
		System.out.println("Soundclips updated");
		System.out.println("------------------------------------");
		
		addStarSoundsHere.addSoundClips(starSoundclips);
		addFlagSoundsHere.addSoundClips(flaggedSoundclips);
		
		printSoundClips();
	}


	private void printSoundClips() {
		
		System.out.println("StarSoundClips:");
		for(SoundClip clip : starSoundclips) {
			System.out.println();
			System.out.println(clip.getName());
		}
		
		System.out.println("------------------------------------");
		System.out.println("FlaggedSoundClips:");
		for(SoundClip clip : flaggedSoundclips) {
			System.out.println();
			System.out.println(clip.getName());
		}
		
		System.out.println("------------------------------------");
		
	}
	
	public ArrayList<SoundClip> getStarSoundclips(){return starSoundclips;}
	public ArrayList<SoundClip> getFlaggedSoundclips(){return flaggedSoundclips;}

	
}
