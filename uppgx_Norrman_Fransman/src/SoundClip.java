

import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */

public class SoundClip{

	private final File file;
	private Album album; 
	private int rating;
	boolean flagged;
	boolean toBeRemoved;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */

	public SoundClip(File file, Album album) {
		assert file != null;
		this.album = album;
		this.file = file;
		
	}
	
	/**
	 * used to create SoundClips with an unknown parent
	 * for temporary SoundClip objects.
	 * @param file
	 * @param parent
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
		//id = parent.getNextId();
	}

	/**
	 * @return the file containing this sound clip.
	 */
	
	public Album getParent() {
		return album;
	}
	
	public File getFile() {
		return file;
	}
	
	/**
	 * returns name of file instead of this. 
	 */
	
	public String getName(){
		return file.getName();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
	
	@Override
	public String toString() {
		
		String flagMessage = "";
		String ratingMessage = "";
		
		if(flagged) {
			flagMessage = " - F";
		} 
		
		if(rating > 0) {
			ratingMessage = " - " + rating;
		}
		
		return getName() + ratingMessage + flagMessage;
	}

	public void setFlagged(boolean x) {flagged = x;}
	
	public boolean getFlagged() {return flagged;}
	
	public void setRating(int rating) {this.rating = rating;}
	
	public int getRating() {return rating;}
	
	public boolean toBeremoved() {return toBeRemoved;}

	public void setToBeRemoved(boolean toBeRemoved) {
		this.toBeRemoved = toBeRemoved;
	}

	
}
