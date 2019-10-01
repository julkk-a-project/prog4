

import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */

public class SoundClip {

	private final File file;
	private int id;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */

	public SoundClip(File file, Album parent) {
		assert file != null;
		this.file = file;
		id = parent.getNextId();
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
	
	public File getFile() {
		return file;
	}
	
	public String toString(){
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
	
	
	public int getId() {
		return id;
	}
}
