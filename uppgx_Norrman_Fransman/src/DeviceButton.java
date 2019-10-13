import java.util.Stack;

public class DeviceButton {
	
	Command theCommand;
	Stack<Command> undoStack;
	Stack<Command> redoStack;
	
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
