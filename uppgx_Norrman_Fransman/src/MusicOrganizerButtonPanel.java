import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MusicOrganizerButtonPanel extends JPanel {

	private MusicOrganizerController controller;
	private MusicOrganizerWindow view;
	
	private JButton newAlbumButton;
	private JButton deleteButton;
	private JButton addSoundClipsButton;
	private JButton removeSoundClipsButton;	
	private JButton playButton;
	private JButton undoButton;
	private JButton redoButton;	

	
	public MusicOrganizerButtonPanel(MusicOrganizerController contr, MusicOrganizerWindow view){
		super(new BorderLayout());

		controller = contr;
		
		this.view = view;
		
		JToolBar toolbar = new JToolBar();
		
		newAlbumButton = createNewAlbumButton();
		toolbar.add(newAlbumButton);

		deleteButton = createDeleteAlbumButton();
		toolbar.add(deleteButton);

		addSoundClipsButton = createAddSoundClipsButton();
		toolbar.add(addSoundClipsButton);

		removeSoundClipsButton = createRemoveSoundClipsButton();
		toolbar.add(removeSoundClipsButton);

		playButton = createPlayButton();
		toolbar.add(playButton);
		
		undoButton = createUndoButton();
		toolbar.add(undoButton);
		
		redoButton = createRedoButton();
		toolbar.add(redoButton);
		
		this.add(toolbar);

	}
	
	/**
	 * Note: You can replace the text strings in the instantiations of the JButtons
	 * below with ImageIcons if you prefer to have buttons with icons instead of
	 * buttons with text strings
	 * 
	 *  Example:
	 *  ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
	 *  JButton newAlbumButton = new JButton(newAlbumIcon);
	 *  
	 *  will put the imageIcon on the button, instead of the text "New Album", as 
	 *  done below
	 * 
	 */
	
	private JButton createNewAlbumButton() {
		ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton(newAlbumIcon);
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addNewAlbum();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton(deleteAlbumIcon);
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeAlbum();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton(addSoundClipsIcon);
		addSoundClipButton.setToolTipText("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				controller.addSoundClips();

			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton(removeSoundClipsIcon);
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeSoundClips();
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton(playIcon);
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	private JButton createRedoButton() {
		JButton redoButton = new JButton("Redo");
		redoButton.setToolTipText("Redo");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		return redoButton;
	}
		
		private JButton createUndoButton() {
			JButton undoButton = new JButton("Undo");
			undoButton.setToolTipText("Undo");
			undoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.undo();
				}
			});
			return undoButton;
		}

}
