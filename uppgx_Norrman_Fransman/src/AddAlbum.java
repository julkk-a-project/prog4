
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
		device.getRedoStack().push(this);
		device.setButtons();
		
		
	}

	@Override
	public void redo() {
		System.out.println("-------------------------------");
		System.out.println(device.getRedoStack().size());
		System.out.println("-------------------------------");
		
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

	

	
}
