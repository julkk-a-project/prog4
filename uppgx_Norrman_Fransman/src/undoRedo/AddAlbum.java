package undoRedo;

import java.util.List;

import musicPlayer.AbstractSoundObject;
import musicPlayer.MusicOrganizerController;

public class AddAlbum extends AbstractChange {

	public AddAlbum(MusicOrganizerController controller, List<AbstractSoundObject> soundObjects) {
		super(controller, soundObjects);
		// TODO Auto-generated constructor stub
	}
	public AddAlbum(MusicOrganizerController controller, AbstractSoundObject soundObject) {
		super(controller, soundObject);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

}
