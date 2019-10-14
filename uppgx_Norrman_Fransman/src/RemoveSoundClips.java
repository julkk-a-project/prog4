import java.util.ArrayList;

public class RemoveSoundClips implements Command {
	
	private MusicOrganizerController device;
	private MusicOrganizerButtonPanel buttons;
	private ArrayList<SoundClip> removedSoundClips;
	private Album parent;
	
	public RemoveSoundClips(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}

	
	public void excecute() {
		
		device.removeSoundClips(this);
		device.getUndoStack().push(this);
		
		device.setButtons();
		
	}


	@Override
	public void undo() {
		
		//to work arround ID system
		removedSoundClips = device.addSoundClips(removedSoundClips, parent);
		device.getUndoStack().pop();
		device.getRedoStack().push(this);
		device.setButtons();
		
	}


	@Override
	public void redo() {
		
		device.removeSoundClips(removedSoundClips, parent);
		device.getUndoStack().push(device.getRedoStack().pop());
		device.setButtons();
		
	}


	@Override
	public Album getAlbum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSoundClips(ArrayList<SoundClip> x) {
		removedSoundClips = x;
	}
	
	public void setParent(Album x) {
		parent = x;
	}


	public ArrayList<SoundClip> getSoundClips() {
		return removedSoundClips;
	}
	
}
