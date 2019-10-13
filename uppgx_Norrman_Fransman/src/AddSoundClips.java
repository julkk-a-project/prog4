
public class AddSoundClips implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	
	public AddSoundClips(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}


	public void excecute() {
		
		device.addSoundClips();
		device.getUndoStack().push(this);
		
		device.setButtons();
	}


	@Override
	public void undo() {
		device.removeSoundClips();
		device.getUndoStack().push(this);
		device.setButtons();
		
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		device.setButtons();
		
	}
	
	
}
