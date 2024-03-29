import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
	private JButton flagButton;
	private JButton rateButton;	
	private JButton undoButton;
	private JButton redoButton;	
	
	private SoundclipGrabber soundclipGrabber = new SoundclipGrabber();
	private Soundclipsobserver observer = new Soundclipsobserver(soundclipGrabber);
	private ArrayList<SoundClip> starSoundclips = new ArrayList<SoundClip>();
	private ArrayList<SoundClip> flaggedSoundclips = new ArrayList<SoundClip>();
	
	boolean beRemoved;

	
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
		
		flagButton = createFlagButton();
		toolbar.add(flagButton);
		
		rateButton = createRateButton();
		toolbar.add(rateButton);
		
		undoButton = createUndoButton();
		//toolbar.add(undoButton);
		
		redoButton = createRedoButton();
		//toolbar.add(redoButton);
		
		this.add(toolbar);

	}
	
	
	private JButton createNewAlbumButton() {
		ImageIcon newAlbumIcon = new ImageIcon("icons/folder_add_32.png");
		JButton newAlbumButton = new JButton(newAlbumIcon);
		newAlbumButton.setEnabled(false);
		newAlbumButton.setToolTipText("New Album");
		newAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.addNewAlbum();
				AddAlbum addAlbumCommand = new AddAlbum(controller, controller.getView().getButtons());
				DeviceButton onPressed = new DeviceButton(addAlbumCommand);
				onPressed.press();
			}
		});
		return newAlbumButton;
	}
	
	private JButton createDeleteAlbumButton() {
		ImageIcon deleteAlbumIcon = new ImageIcon("icons/folder_delete_32.png");
		JButton deleteAlbumButton = new JButton(deleteAlbumIcon);
		deleteAlbumButton.setEnabled(false);
		deleteAlbumButton.setToolTipText("Delete Selected Album");
		deleteAlbumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.removeAlbum();
				RemoveAlbum removeAlbumCommand = new RemoveAlbum(controller, controller.getView().getButtons());
				DeviceButton onPressed = new DeviceButton(removeAlbumCommand);
				onPressed.press();
			}
		});
		return deleteAlbumButton;
	}

	private JButton createAddSoundClipsButton() {
		ImageIcon addSoundClipsIcon = new ImageIcon("icons/document_add_32.png");
		JButton addSoundClipButton = new JButton(addSoundClipsIcon);
		addSoundClipButton.setEnabled(false);
		addSoundClipButton.setToolTipText ("Add Selected Sound Clips To Selected Album");
		addSoundClipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				AddSoundClips addSoundClipsCommand = new AddSoundClips(controller, controller.getView().getButtons());
				DeviceButton onPressed = new DeviceButton(addSoundClipsCommand);
				onPressed.press();

			}
		});
		return addSoundClipButton;
	}
	
	private JButton createRemoveSoundClipsButton() {
		ImageIcon removeSoundClipsIcon = new ImageIcon("icons/document_delete_32.png");
		JButton removeSoundClipsButton = new JButton(removeSoundClipsIcon);
		removeSoundClipsButton.setEnabled(false);
		removeSoundClipsButton.setToolTipText("Remove Selected Sound Clips From Selected Album");
		removeSoundClipsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//controller.removeSoundClips();
				RemoveSoundClips removeSoundClipsCommand = new RemoveSoundClips(controller, controller.getView().getButtons());
				DeviceButton onPressed = new DeviceButton(removeSoundClipsCommand);
				onPressed.press();
			}
		});
		return removeSoundClipsButton;
	}
	
	private JButton createPlayButton() {
		ImageIcon playIcon = new ImageIcon("icons/play_32.png");
		JButton playButton = new JButton(playIcon);
		playButton.setEnabled(false);
		playButton.setToolTipText("Play Selected Sound Clip");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.playSoundClips();
			}
		});
		return playButton;
	}
	
	private JButton createRedoButton() {
		ImageIcon redoIcon = new ImageIcon("icons/redo.png");
		JButton redoButton = new JButton(redoIcon);
		redoButton.setVisible(false);
		redoButton.setToolTipText("Redo");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nRedo Button Pressed\n");
				System.out.println("There is " + controller.getRedoStack().size() + " commands in your redo stack.");
				controller.getRedoStack().peek().redo();
				System.out.println("\nRedo Excecuted\n");
				System.out.println("There is now " + controller.getRedoStack().size() + " commands left in your redo stack.");
				System.out.println("\nAnd " + controller.getUndoStack().size() + " commands left in your undo stack.\n");
				System.out.println("\n\n\n");

			}
		});
		return redoButton;
	}

	private JButton createUndoButton() {
		ImageIcon undoIcon = new ImageIcon("icons/undo.png");
		JButton undoButton = new JButton(undoIcon);
		undoButton.setVisible(false);
		undoButton.setToolTipText("Undo");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nUndo Button Pressed\n");
				System.out.println("There is " + controller.getUndoStack().size() + " commands in your undo stack.");
				controller.getUndoStack().peek().undo();
				System.out.println("\nUndo Excecuted\n");
				System.out.println("There is now " + controller.getUndoStack().size() + " commands left in your undo stack.");
				System.out.println("\nAnd " + controller.getRedoStack().size() + " commands left in your redo stack.\n");
				System.out.println("\n\n\n");
				if(controller.getUndoStack().empty())
					undoButton.setVisible(false);
				else
					undoButton.setVisible(true);
			}
		});
		return undoButton;
	}
	
	private JButton createFlagButton() {
		ImageIcon flagIcon = new ImageIcon("icons//Actions-flag-icon.png");
		JButton flagButton = new JButton(flagIcon);
		flagButton.setEnabled(false);
		flagButton.setVisible(true);
		flagButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(view.getSelectedSoundClips().toString().equals("[]") || view.getSelectedSoundClips() == null) {
					view.showMessage("Soundclip needs to be selected");
				}
				
				for(int i = 0; i < view.getSelectedSoundClips().size(); i++) {
					if(view.getSelectedSoundClips().get(i).getFlagged()) {
						view.getSelectedSoundClips().get(i).setToBeRemoved(true);
						view.getSelectedSoundClips().get(i).setFlagged(false);
					}
					else {
						view.getSelectedSoundClips().get(i).setToBeRemoved(false);
						view.getSelectedSoundClips().get(i).setFlagged(true);
					}	
				}

				for(SoundClip clip : view.getSelectedSoundClips()) { //adds flagged soundclips
					flaggedSoundclips.add(clip);
				}
				
				soundclipGrabber.setSoundClips(starSoundclips, flaggedSoundclips, controller.getStarAlbum(), controller.getFlagAlbum());
				flaggedSoundclips.clear();
				view.getClipTable().display(controller.getDoubleSelectedAlbum());
			}	
		});
		return flagButton;
	}

	private JButton createRateButton() {
		ImageIcon rateIcon = new ImageIcon("icons//favourites_32.png");
		JButton rateButton = new JButton(rateIcon);
		rateButton.setEnabled(false);
		rateButton.setVisible(true);
		rateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(view.getSelectedSoundClips().toString().equals("[]") || view.getSelectedSoundClips() == null) {
					view.showMessage("Soundclip needs to be selected");
				} else {

					int score = Integer.parseInt(JOptionPane.showInputDialog(null, "Set rating 0-5. ")); //gets user input

					if(score >= 0 && score <= 5) { //cheks if correct input

						for(SoundClip clip : view.getSelectedSoundClips()) {
							clip.setRating(score);


							if(score > 3 && score <= 5) { //cheks if is favourite clip

								starSoundclips.add(clip);
								clip.setToBeRemoved(false);
							} else {
								starSoundclips.add(clip);
								clip.setToBeRemoved(true);
							}

							soundclipGrabber.setSoundClips(starSoundclips, flaggedSoundclips, controller.getStarAlbum(), controller.getFlagAlbum());
							starSoundclips.clear();
						}
						view.getClipTable().display(controller.getDoubleSelectedAlbum());
					}
					else {
						view.showMessage("Ratings from 0-5.");
					}

				}
			}
		});
		return rateButton;
	}

		public JButton getRedoButton() {
			return redoButton;
		}
		
		public JButton getUndoButton() {
			return undoButton;
		}

		public void setAddAlbumButton(boolean b) {
			newAlbumButton.setEnabled(b);
		}

		public void setRemoveAlbumButton(boolean b) {
			deleteButton.setEnabled(b);
			
		}
		
		public void setAddSoundClipsButton(boolean b) {
			addSoundClipsButton.setEnabled(b);
			
		}
		
		public void setRemoveSoundClipsButton(boolean b) {
			removeSoundClipsButton.setEnabled(b);
			
		}

		public void setPlayButton(boolean b) {
			playButton.setEnabled(b);
			
		}

		public void setRateButton(boolean b) {
			rateButton.setEnabled(b);
			
		}

		public void setFlagButton(boolean b) {
			flagButton.setEnabled(b);
			
		}
		
		}


