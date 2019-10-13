
public class RemoveAlbum implements Command {
	
	MusicOrganizerController device;
	MusicOrganizerButtonPanel buttons;
	Album removedAlbum;
	Album removedAlbumParent;
	
	public RemoveAlbum(MusicOrganizerController newDevice, MusicOrganizerButtonPanel buttons) {
		
		device = newDevice;
		this.buttons = buttons;
		
	}

	public void excecute() {
		
		device.removeAlbum(this);
		device.getUndoStack().push(this);
		
		device.setButtons();
		
	}

	@Override
	public void undo() {
		device.addNewAlbum(removedAlbum);
		device.getRedoStack().push(this);
		device.setButtons();
		
	}

	@Override
	public void redo() {
		
		device.removeAlbum(removedAlbum);
		device.getUndoStack().push(device.getRedoStack().pop());
		device.setButtons();
		
	}
	
	public void setAlbum(Album x) {

		removedAlbum = x;

	}
	
	public void setAlbumParent(Album x) {

		removedAlbumParent = x;

	}

}

