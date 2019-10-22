import java.util.ArrayList;

public class AddAlbum implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	Album addedAlbum;
	Album addedAlbumParent;
	
	
	public AddAlbum(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}
	
	@Override
	public void excecute() {
		
		device.addNewAlbum(this);
		device.getUndoStack().push(this);
		
		
		device.setButtons();
		
	}

	@Override
	public void undo() {
		
		device.removeAlbum(addedAlbum);
		device.getUndoStack().pop();
		device.getRedoStack().push(this);
		device.setButtons();
		
		
	}

	@Override
	public void redo() {
		device.addNewAlbum(addedAlbum);
		device.getUndoStack().push(device.getRedoStack().pop());
		device.setButtons();
		
	}

	public void setAlbum(Album x) {
		
		addedAlbum = x;
		
	}
	
	public void setAlbumParent(Album x) {

		
		addedAlbumParent = x;

	}
	
	public Album getAlbum() {
		return addedAlbum;
	}

	public void setSoundClips(ArrayList<SoundClip> soundClips) {
		addedAlbum.setSoundClips(soundClips);
	}

	
}
