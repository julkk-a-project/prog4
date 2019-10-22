import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractAlbum {
	
	private Album parent;
	private String name;
	private ArrayList<SoundClip> soundClips = new ArrayList<SoundClip>();
	protected boolean isSearchAlbum = true; //the lazy way


	//what is called when soundClips are read from an abstract album.
	public abstract  ArrayList<SoundClip> getSoundClipsRec();
	
	
	
	
	public Album getParent() {
		return parent;
	}
	public void setParent(Album parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//"primitive" getSoundClips. not for getting actual sound clips in recursive child tree
	public ArrayList<SoundClip> getSoundClips() {
		return soundClips;
	}
	
	public void setSoundClips(ArrayList<SoundClip> soundClips) {
		this.soundClips = soundClips;
	}
	
	//to get specific index of soundclip
	public SoundClip getSoundClip(int index) {
		return getSoundClips().get(index);
	}

	
	

	/**
	 * to make name show in tree instead of object reference.
	 */
	@Override
	public String toString() {
		return getName();
	}
	

	
	
	public void addSoundClip(File inFile) { //Lägger till en fil i albummet och skapar en SoundClip för filen

		getSoundClips().add(new SoundClip(inFile, this));

	}
	

	
	public void addSoundClip(SoundClip soundClip) { //Lägger till en färdig soundclip objekt i listan där de sparas i albumet!

		//SoundClip newSoundClip = new SoundClip(soundClip.getFile(), this);
		getSoundClips().add(soundClip);
		//return newSoundClip.getId();
	 
	}

	//lägger till en mängd soundclips till albumet.
	public void addSoundClips(Set<SoundClip> soundClips) {

		for (SoundClip j : soundClips) {
			addSoundClip(j);
		}
		
	}
	
	

	
	
	
	
	/**
	 * Kollar om det finns soundclips att radera.
	 * går igenom alla soundclips tills den hittar rätt och tar bort det.
	 */
	public boolean removeSoundClip(SoundClip soundClipToBeRemoved) {
			
		
		
		//Precondition
		assert (getSoundClips().size() > 0): "No soundclips to be removed";
				
		for(int i = 0; i < getSoundClips().size(); i++) { 
			
			if(getSoundClips().get(i).getName().equals(soundClipToBeRemoved.getName())) {
						
				//Postcondition
				assert(getSoundClips().remove(i) != null): "No soundclip was removed";
				
				return true;
						
			}
					
		}
			
		return false;
			
		}
	

	/**
	 * Kollar om det finns soundclips att radera.
	 * går igenom alla soundclips tills den hittar rätt och tar bort de.
	 */
	public boolean removeSoundClips(List<SoundClip> soundClipsToBeRemoved) {
			

		//Precondition
		assert (getSoundClips().size() > 0): "No soundclips to be removed";
		
		
		for (SoundClip soundClipToBeRemoved:soundClipsToBeRemoved) {

			
			
					
			for(int i = 0; i < getSoundClips().size(); i++) { 
						
				
				if(getSoundClips().get(i).getName().equals(soundClipToBeRemoved.getName())) {
							
					getSoundClips().remove(i);			
							
					break;
				}
						
			}
				
		}
			
		return false;
			
		}
	



	public void addSoundClips(ArrayList<SoundClip> addedSoundClips) {
		Set<SoundClip> set = new HashSet<SoundClip>();
		set.addAll(addedSoundClips);
		addSoundClips(set);
		
		//to make ID system work with undoredo
		//return getLastAddedSoundClips();
	}




	public boolean isSearchAlbum() {
		return isSearchAlbum;
	}

	
	
	

	
	

}
