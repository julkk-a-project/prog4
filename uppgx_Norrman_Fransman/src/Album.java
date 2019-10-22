

import java.util.ArrayList;

public class Album extends AbstractAlbum {

	private ArrayList<AbstractAlbum> subAlbums = new ArrayList<AbstractAlbum>();

	

	public Album(String name) {  //skapar rot album

		this.isSearchAlbum = false;
		this.setName(name);

	}

	public Album(Album parent, String name) { //skapar sub album
		
		this.isSearchAlbum = false;
		this.setParent(parent);
		this.setName(name);

	} 
	
	
	public ArrayList<AbstractAlbum> getSubAlbums() {
		return subAlbums;
	}

	
	
	public void setSubAlbumList(ArrayList<AbstractAlbum> x) {
		subAlbums = x;
	}
	

	/**
	 * returns all soundclips, and recursevly also all subalbum's soundclips.
	 * 
	 */
	public ArrayList<SoundClip> getSoundClipsRec() {
		ArrayList<SoundClip> soundClipsToBeShown = new ArrayList<>();
		soundClipsToBeShown.addAll(getSoundClips());
	
		
		
		if(subAlbums.size() > 0) {
			for (AbstractAlbum a : subAlbums) {
				try {
					Album temp = (Album) a;
					soundClipsToBeShown.addAll(temp.getSoundClipsRec());	
				} catch(ClassCastException e) {
					
				}
				
			}	
		}
		
		return soundClipsToBeShown;
	}
	
	
	
	
	
	
	
	
	
	public boolean addSubAlbum(AbstractAlbum newAlbum) { //Lägger till ett sub album
		
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