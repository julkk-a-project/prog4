

import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */

public class SoundClip{

	private final File file;
	private int id;
	private AbstractAlbum album; 
	private int rating;
	boolean flagged;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */

	public SoundClip(File file, AbstractAlbum album) {
		assert file != null;
		this.album = album;
		this.file = file;
		id = album.getNextId();
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
	
	public AbstractAlbum getParent() {
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
	
	
	
	
//	/**
//	 * gives ID to compare with
//	 * @return
//	 */
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int i) {
//		id = i;
//		
//	}
	
	
	

	
}
