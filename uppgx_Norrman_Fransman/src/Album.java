

import java.io.File;
import java.util.ArrayList;

public class Album {

	private Album parent;
	private String name;
	private ArrayList<Album> subAlbums = new ArrayList<Album>();
	private ArrayList<SoundClip> soundClips = new ArrayList<SoundClip>();
	
	
	public Album(String name) {  //skapar rot album
		
		this.name = name;

	}
	
	public Album(Album parent, String name) { //skapar sub album
		
		this.parent = parent;
		this.name = name;
		
	}
	
	//getters and setters
	
	public Album getParent() {
		return parent;
	}
	
	public String getName() {
		return name;
	}
	
	//TODO: maybe add specific subAlbum getter?
	public ArrayList<Album> getSubAlbums() {
		return subAlbums;
	}

	public ArrayList<SoundClip> getSoundClips() {
		return soundClips;
	}

	//to get specific index of soundclip
	public SoundClip getSoundClip(int index) {
		return soundClips.get(index);
	}
	
	//to get size of soundClips. incase we change how we calculate size of soundClips (such as with an int field :informationdeskwoman_emoji:
	public int getSoundClipsSize() {
		return soundClips.size();
	}
	
	
	
	
	
	
	//Setters
	
	
	//maybe don't use this...
	//private void setParent(Album newParent);
	
	
	//should be legit. idk, consult partner.
	public void setName(String newName) {
		name = newName;
	}
	
	
	//setSubALbums and setSoundClips prob should not be used...
	//this is why they should be private, appart from being good practice! ;)
	
	
	
	
	
	
	
	
	
	public boolean addSubAlbum(Album newAlbum) { //Lägger till ett sub album
		
		subAlbums.add(newAlbum);
		return true;
		
	}
	
	
	/*
	 * Kollar om det finns subalbum att radera.
	 * går igenom alla subalbum tills den hittar rätt och tar bort det samt dess subalbum.
	 */
	
	public boolean removeSubAlbum(Album albumToBeRemoved) {
		
		//Precondition
		assert(subAlbums.size() > 0): "No subalbums exist.";
		
		for(int i = 0; i < subAlbums.size(); i++) { 
				
				if(subAlbums.get(i).equals(albumToBeRemoved)) {
					
					//Postcondition
					assert(subAlbums.remove(i) != null): "Nothing was removed";
					
					return true;
					
					}
					
				}	
		
		return false;
		
	}


	
	public void addSoundClip(File inFile) { //Lägger till en fil i albummet och skapar en SoundClip för filen
		
		soundClips.add(new SoundClip(inFile));
		
	}
	
	
	/*
	 * Kollar om det finns soundclips att radera.
	 * går igenom alla soundclips tills den hittar rätt och tar bort det.
	 */
	
	
	public boolean removeSoundClip(SoundClip soundClipToBeRemoved) { 
			
		//Precondition
		assert (soundClips.size() > 0): "No soundclips to be removed";
				
		for(int i = 0; i < soundClips.size(); i++) { 
					
			if(soundClips.get(i).toString().equals(soundClipToBeRemoved.toString())) {
						
				//Postcondition
				assert(soundClips.remove(i) != null): "No soundclip was removed";
				
				return true;
						
			}
					
		}
			
		return false;
			
		}
	
	/*
	 * Söker igenom ett albums ljudklipp och gemför namnet med det ljudklippet
	 * vi vill hitta.
	 */
	
	public SoundClip findSoundClip(String name) {
		
		//Precondition
		assert (soundClips.size() > 0): "This album has no soundclips";
		
		for (int i = 0; i < soundClips.size(); i++) {
			
			if(soundClips.get(i).getFile().toString().equals(name)) {
				
				return soundClips.get(i);
				
			}
					
		}
		
		return null;
		
	}
	
}

