

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Album {

	private Album parent;
	private String name;
	private ArrayList<Album> subAlbums = new ArrayList<Album>();
	private ArrayList<SoundClip> soundClips = new ArrayList<SoundClip>();
	private int idToSoundClip = 0;
	
	
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
		ArrayList<SoundClip> soundClipsToBeShown = new ArrayList<>();
		soundClipsToBeShown.addAll(soundClips);
		int i = 0;
		
		System.out.println(this.toString());

		System.out.println(subAlbums.toString()+" "+i);
		
		for(int j = 0; j < subAlbums.size(); j++) {
			System.out.println(j+"gaygaygay");
		}
		
		for (Album a : subAlbums) {
			System.out.println(a.toString()+" "+i++);
			ArrayList<SoundClip> newStuff = a.getSoundClips();
			soundClipsToBeShown.addAll(newStuff);
		}
		return soundClipsToBeShown;
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
	
	
	
	
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
	
	
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

		soundClips.add(new SoundClip(inFile, this));

	}
	

	//this can't happen because we have the ID System, which requiers the parent album to be known at creation. :(
	//nvm, just understood that i could do it this way.
	
	public void addSoundClip(SoundClip soundClip) { //Lägger till en färdig soundclip objekt i listan där de sparas i albumet!

	 soundClips.add(new SoundClip(soundClip.getFile(), this));

	}

	public void addSoundClips(Set<SoundClip> soundClips) {

		for (SoundClip i : soundClips) {
			addSoundClip(i);
		}

	}

	/*
	public void addSoundClips(Set<File> files) {
		
		for (File i : files) {
			addSoundClip(i);
		}
		
	}*/
	
	
	/*
	 * Kollar om det finns soundclips att radera.
	 * går igenom alla soundclips tills den hittar rätt och tar bort det.
	 */
	
	
	public boolean removeSoundClip(SoundClip soundClipToBeRemoved) {
			
		
		
		//Precondition
		assert (soundClips.size() > 0): "No soundclips to be removed";
				
		for(int i = 0; i < soundClips.size(); i++) { 
					
			if(soundClips.get(i).getId() == soundClipToBeRemoved.getId()) {
						
				//Postcondition
				assert(soundClips.remove(i) != null): "No soundclip was removed";
				
				return true;
						
			}
					
		}
			
		return false;
			
		}
	
	
	public boolean removeSoundClips(List<SoundClip> soundClipsToBeRemoved) {
			

		//Precondition
		assert (soundClips.size() > 0): "No soundclips to be removed";
		
		
		for (SoundClip soundClipToBeRemoved:soundClipsToBeRemoved) {

			
			
					
			for(int i = 0; i < soundClips.size(); i++) { 
						
				
				if(soundClips.get(i).getId() == (soundClipToBeRemoved.getId())) {
							
					soundClips.remove(i);			
							
					break;
				}
						
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

	public int getNextId() {
		
		return idToSoundClip++;
		
	}
	
}

