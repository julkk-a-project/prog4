package undoRedo;

import java.util.ArrayList;
import java.util.List;

import musicPlayer.AbstractSoundObject;
import musicPlayer.MusicOrganizerController;

public abstract class AbstractChange {

	protected MusicOrganizerController controller;
	protected List<AbstractSoundObject> soundObjects;

	
	/**
	 * constructor for multiple objects.
	 * @param controller
	 * @param soundObjects
	 */
	public AbstractChange(MusicOrganizerController controller, List<AbstractSoundObject> soundObjects){
		//superconstructor
		this.controller = controller;
		this.soundObjects = soundObjects;
	}
	
	
	/**
	 * constructor for single objects ;)))
	 * @param controller
	 * @param soundObject
	 */
	public AbstractChange(MusicOrganizerController controller, AbstractSoundObject soundObject){
		//superconstructor
		this.controller = controller;
		
		List<AbstractSoundObject> soundObjects = new ArrayList<AbstractSoundObject>();
		soundObjects.add(soundObject);
		this.soundObjects = soundObjects;
	}
	
	
	public abstract void undo();
	public abstract void redo();
	
}
