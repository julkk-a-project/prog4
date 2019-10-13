import java.util.ArrayList;

public class AddSoundClips implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	ArrayList<SoundClip> addedSoundClips;
	
	public AddSoundClips(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}


	public void excecute() {
		
		device.addSoundClips(this);
		device.getUndoStack().push(this);
		
		device.setButtons();
	}


	@Override
	public void undo() {
		device.removeSoundClips();
		device.getUndoStack().pop();
		device.getRedoStack().push(this);
		device.setButtons();
		
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		excecute();
		device.setButtons();
		
	}


	@Override
	public Album getAlbum() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSoundClips(ArrayList<SoundClip> x) {
		addedSoundClips = x;
	}
	
	
}
