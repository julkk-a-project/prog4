import java.util.ArrayList;

public class Soundclipsobserver implements Observer {

	private ArrayList<SoundClip> starSoundclips;
	private ArrayList<SoundClip> flaggedSoundclips;
	
	private Subject soundclipGrabber;
	
	
	public Soundclipsobserver(Subject soundclipGrabber) {
		
		this.soundclipGrabber = soundclipGrabber;
		
		starSoundclips = new ArrayList<SoundClip>();
		flaggedSoundclips = new ArrayList<SoundClip>();
		
		System.out.println("New Observer Created");
		
		soundclipGrabber.register(this);
		
	}
	
	
	public void update(ArrayList<SoundClip> starSoundclips, ArrayList<SoundClip> flaggedSoundclips, StarAlbum addStarSoundsHere, FlagAlbum addFlagSoundsHere) {
		
		if(!starSoundclips.isEmpty()) { //kollar om tom

			for(SoundClip clip: starSoundclips) {
				if(!clip.toBeremoved()) { //om den inte ska tas bort ska den addas
					this.starSoundclips.add(clip);
				} else {	
					this.starSoundclips.remove(clip); //tas bort	
				}
				
			}
		} 
		if(!flaggedSoundclips.isEmpty()){ //kollar om tom

			for(SoundClip clip: flaggedSoundclips) {
				if(!clip.toBeremoved()) { //om den inte ska tas bort ska den addas
					this.flaggedSoundclips.add(clip);
				} else {	
					this.flaggedSoundclips.remove(clip); //tas bort	
				}
			}
		}
		System.out.println("Soundclips updated");
		System.out.println("------------------------------------");
		
		addStarSoundsHere.addSoundClips(starSoundclips); //Läggs till i våra sök album
		addFlagSoundsHere.addSoundClips(flaggedSoundclips);
		
		printSoundClips();
	}


	private void printSoundClips() {  //print funktion
		
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
