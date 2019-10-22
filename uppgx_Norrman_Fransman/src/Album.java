

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Album extends AbstractAlbum {

//	private Album parent;
//	private String name;
	private ArrayList<Album> subAlbums = new ArrayList<Album>();
//	private ArrayList<SoundClip> soundClips = new ArrayList<SoundClip>();
//	private int idToSoundClip = 0;
//	private ArrayList<SoundClip> lastAddedSoundClips = new ArrayList<SoundClip>();
	
	
	public Album(String name) {  //skapar rot album
		
		this.setName(name);

	}
	
	public Album(Album parent, String name) { //skapar sub album
		
		this.setParent(parent);
		this.setName(name);
		
	} 
	
	
	public ArrayList<Album> getSubAlbums() {
		return subAlbums;
	}

	
	
	public void setSubAlbumList(ArrayList<Album> x) {
		subAlbums = x;
	}
	

	/**
	 * returns all soundclips(, and recursevly also all subalbum's soundclips.)
	 * 
	 */
	public ArrayList<SoundClip> getSoundClips() {
		ArrayList<SoundClip> soundClipsToBeShown = new ArrayList<>();
		soundClipsToBeShown.addAll(getSoundClipsList());
	
		
		
		//this ifstatement makes undo/redo not work.
		if(subAlbums.size() > 0) {
			for (Album a : subAlbums) {
				soundClipsToBeShown.addAll(a.getSoundClips());
			}	
		}
		
		return soundClipsToBeShown;
	}
	
	
	
	
	
	
	
	
	
	public boolean addSubAlbum(Album newAlbum) { //Lägger till ett sub album
		
		getSubAlbums().add(newAlbum);
		return true;
		
	}
	
	
	/**
	 * Kollar om det finns subalbum att radera.
	 * går igenom alla subalbum tills den hittar rätt och tar bort det samt dess subalbum.
	 */
	public boolean removeSubAlbum(Album albumToBeRemoved) {
		
		//Precondition
		assert(getSubAlbums().size() > 0): "No subalbums exist.";
		
		for(int i = 0; i < getSubAlbums().size(); i++) { 
				
				if(getSubAlbums().get(i).equals(albumToBeRemoved)) {
					
					//Postcondition
					assert(getSubAlbums().remove(i) != null): "Nothing was removed";
					
					return true;
					
					}
					
				}	
		
		return false;
		
	}



}