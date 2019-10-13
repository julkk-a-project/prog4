package undoRedo;

import java.util.List;

import musicPlayer.AbstractSoundObject;
import musicPlayer.MusicOrganizerController;

public class AddSoundClip extends AbstractChange {

	public AddSoundClip(MusicOrganizerController controller, List<AbstractSoundObject> soundObjects) {
		super(controller, soundObjects);
		// TODO Auto-generated constructor stub
	}
	public AddSoundClip(MusicOrganizerController controller, AbstractSoundObject soundObject) {
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
