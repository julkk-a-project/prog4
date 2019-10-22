import javax.swing.SwingUtilities;

public class MainApplication {

	/**
	 * Main entry point of music organizer.
	 * @param args - the command line arguments. If specified, it should contain the path to 
	 * the folder where to find the desired WAV files to use in the application.
	 */
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				MusicOrganizerController controller = new MusicOrganizerController();
			
			}
		});
	}

}
