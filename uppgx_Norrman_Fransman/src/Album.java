

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
	
	public void setParent(Album x) {
		parent = x;
	}
	
	public String getName() {
		return name;
	}
	
	
	public ArrayList<Album> getSubAlbums() {
		return subAlbums;
	}

	
	
	/**
	 * returns all soundclips, and recursevly also all subalbum's soundclips.
	 * 
	 */
	public ArrayList<SoundClip> getSoundClips() {
		ArrayList<SoundClip> soundClipsToBeShown = new ArrayList<>();
		soundClipsToBeShown.addAll(soundClips);
		
		for (Album a : subAlbums) {
			soundClipsToBeShown.addAll(a.getSoundClips());
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
	
	
	
	//to change name, might be usefull in future.
	public void setName(String newName) {
		name = newName;
	}
	
	
	public void setSubAlbumList(ArrayList<Album> x) {
		this.subAlbums = x;
	}
	
	public void setSoundClipList(ArrayList<SoundClip> x) {
		this.soundClips = x;
	}
	
	
	/**
	 * to make name show in tree instead of object reference.
	 */
	@Override
	public String toString() {
		return name;
	}
	
	
	
	
	public boolean addSubAlbum(Album newAlbum) { //Lägger till ett sub album
		
		subAlbums.add(newAlbum);
		return true;
		
	}
	
	
	/**
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
	

	
	public void addSoundClip(SoundClip soundClip) { //Lägger till en färdig soundclip objekt i listan där de sparas i albumet!

	 soundClips.add(new SoundClip(soundClip.getFile(), this));

	}

	//lägger till en mängd soundclips till albumet.
	public void addSoundClips(Set<SoundClip> soundClips) {

		for (SoundClip i : soundClips) {
			addSoundClip(i);
		}

	}
	
	
	
	
	/**
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
	

	/**
	 * Kollar om det finns soundclips att radera.
	 * går igenom alla soundclips tills den hittar rätt och tar bort de.
	 */
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
	
	/**
	 * (Legacycode)
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


	/**
	 * used to get the next ID from the album object.
	 * the ID should be given unicley to each soundclip.
	 * 
	 * please don't overuse me unless you are my(this object) child :(
	 * 
	 * this ID system is used to differenciate SoundClip objects in a specific album.
	 * 
	 * 
	 * @return
	 */
	 synchronized int getNextId() {
		
		return idToSoundClip++;
		
	}
	
}

