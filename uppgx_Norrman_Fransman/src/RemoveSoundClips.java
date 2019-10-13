
public class RemoveSoundClips implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	
	public RemoveSoundClips(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}

	
	public void excecute() {
		
		device.removeSoundClips();
		device.getUndoStack().push(this);
		
		device.setButtons();
		
	}


	@Override
	public void undo() {
		device.addSoundClips();
		device.getUndoStack().push(this);
		device.setButtons();
		
	}


	@Override
	public void redo() {
		// TODO Auto-generated method stub
		device.setButtons();
		
	}

	
}
