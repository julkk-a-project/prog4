import java.util.ArrayList;

public class AddSoundClips implements Command {
	
	private MusicOrganizerController device;
	private MusicOrganizerButtonPanel buttons;
	private ArrayList<SoundClip> addedSoundClips;
	private Album parent;
	
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
		
		device.removeSoundClips(addedSoundClips, parent);
		device.getUndoStack().pop();
		device.getRedoStack().push(this);
		device.setButtons();
		
	}


	@Override
	public void redo() {
		
		System.out.println("SOUNDCLIPS");
		device.addSoundClips(addedSoundClips, parent);
		device.getUndoStack().push(device.getRedoStack().pop());
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
	
	public void setParent(Album x) {
		parent = x;
	}
	
	
}
