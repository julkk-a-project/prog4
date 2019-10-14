

public class DeviceButton {
	
	Command theCommand;
	
	public DeviceButton(Command newCommand) {
		
		theCommand = newCommand;
		
	}
	
	public void press() {
		
		theCommand.excecute();
		
	}
	
	public void pressUndo() {
		
		theCommand.undo();
		
	}
	
	public void pressRedo() {

		theCommand.redo();
		
	}
	
}
