import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JList;

public class SoundClipTable extends JList {

	private List<SoundClip> clips;
	
	
	public SoundClipTable() {
		super();
		clips = new ArrayList<SoundClip>();
	}
	
	/**
	 * Displays the contents of the specified album
	 * @param a - the album which contents are to be displayed
	 */
	public void display(ADD_YOUR_ALBUM_TYPE a){
		this.clearTable();
		
		// TODO: Add all sound clips found in 'a'
		// to the instance variable 'clips'.
		//
		// Something like this:
		//
		// clips.addAll(a.getAllSoundClips());
		
		Object[] data = new Object[clips.size()];
		Iterator<SoundClip> it = clips.iterator();
		int i = 0;
		while(it.hasNext()){
			SoundClip s = it.next();
			data[i++] = s.toString();
		}
		this.setListData(data);

		invalidate();
		validate();
		doLayout();
		repaint();
	}
	
	/**
	 * Clears the contents of the table and the clips List
	 */
	private void clearTable(){
		clips.removeAll(clips);
		this.setListData(new String[0]);
	}
	
	/**
	 * Returns a List of all the SoundClips at the specified indices
	 * @param indices of selected clips
	 * @return List of SoundClips at the indices
	 */
	public List<SoundClip> getClips(int[] indices){
		List<SoundClip> l = new ArrayList<SoundClip>();
		for(int i=0;i<indices.length;i++){
			l.add(clips.get(indices[i]));
		}
		return l;
	}

}
