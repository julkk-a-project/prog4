
public class RemoveSoundClips implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	
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
		device.addSoundClips();
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

	
}
