
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.MouseInputAdapter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;


public class MusicOrganizerWindow extends JFrame {

	private static final long serialVersionUID = 3730280597997154220L;
	private static int DEFAULT_WINDOW_WIDTH = 900;
	private static int DEFAULT_WINDOW_HEIGHT = 600;

	

	private final JTree albumTree;
	private final SoundClipTable clipTable;
	private MusicOrganizerButtonPanel buttonPanel;
	private MusicOrganizerController controller;
	
	public MusicOrganizerWindow(MusicOrganizerController contr) {

		// Store a reference to the controller
		controller = contr;
		
		// make the row of buttons
		buttonPanel = new MusicOrganizerButtonPanel(controller, this);
		
		// make the album tree
		albumTree = makeCatalogTree();
		
		// make the clip table
		clipTable = makeClipTable();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(albumTree), new JScrollPane(clipTable));
		splitPane.setDividerLocation(DEFAULT_WINDOW_WIDTH/2);
		
		// Place the buttonpanel above the two Jscrollpanes
		JSplitPane horizontalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, splitPane);

		this.add(horizontalSplit);
				
		// give the whole window a good default size
		this.setTitle("Music Organizer");
		this.setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);

		// end the program when the user presses the window's Close button
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		this.setVisible(true);
		
	}

	/**
	 * Make the tree showing album names. 
	 */
	private JTree makeCatalogTree() {
		

		DefaultMutableTreeNode tree_root = new DefaultMutableTreeNode();
		tree_root.setUserObject((ADD_YOUR_ALBUM_TYPE) controller.getRootAlbum());
		
		final JTree tree = new JTree(tree_root);
		tree.setMinimumSize(new Dimension(200, 400));
		
		tree.setToggleClickCount(3); // so that we can use double-clicks for
										// previewing instead of
										// expanding/collapsing

		DefaultTreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
		selectionModel
				.setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setSelectionModel(selectionModel);

		tree.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if left-double-click @@@changed =2 to ==1
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
					
					// TODO YOUR CODE HERE
					// The code here gets invoked whenever the user double clicks in the album tree
					
					
					System.out.println("show the sound clips for album " + getSelectedTreeNode().getUserObject());
				}
			}
		});

		return tree;
	}

	/**
	 * Make the table showing sound clips
	 */
	private SoundClipTable makeClipTable(){
		SoundClipTable table = new SoundClipTable();
		
		table.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if left-double-click @@@changed =2 to ==1
				if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
					
					// TODO YOUR CODE HERE
					// The code here gets invoked whenever the uses double clicks on the list of sound clips
					
					System.out.println("clicked on clipTable");
					
					
				}
			}
		});
		return table;
	}
	
	/**
	 * Pop up a dialog box prompting the user for a name for a new album.
	 * Returns the name, or null if the user pressed Cancel
	 */
	public String promptForAlbumName() {
		return (String) JOptionPane.showInputDialog(
				albumTree,
				"Album Name: ",
				"Add Album",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"");
	}

	/**Creates a pop up window showing a message
	 * @param message - the message to display
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Return the album currently selected in the album tree. Returns null if no
	 * selection.
	 */
	private DefaultMutableTreeNode getSelectedTreeNode() {
		return  (DefaultMutableTreeNode) albumTree.getLastSelectedPathComponent();
	}
	
	
	/**
	 * Return all the sound clips currently selected in the clip table.
	 */
	public List<SoundClip> getSelectedSoundClips(){
		return clipTable.getClips(clipTable.getSelectedIndices());
	}
	
	/**
	 * Return the album currently selected in the album tree. Returns null if no
	 * selection.
	 * @return the selected album
	 */
	public ADD_YOUR_ALBUM_TYPE getSelectedAlbum() {
		DefaultMutableTreeNode node = getSelectedTreeNode();
		if(node != null) {
			return (ADD_YOUR_ALBUM_TYPE) node.getUserObject();
		} else {
			return null;
		}
	}
	
	
	
	
	/**
	 * *****************************************************************
	 * Methods to be called in response to events in the Music Organizer
	 * *****************************************************************
	 */
	
	
	
	/**
	 * Updates the album hierarchy with a new album
	 * @param newAlbum
	 */
	public void onAlbumAdded(ADD_YOUR_ALBUM_TYPE newAlbum){
		
		assert newAlbum != null;
		
		DefaultTreeModel model = (DefaultTreeModel) albumTree.getModel();
		
		//We search for the parent of the newly added Album so we can create the new node in the correct place
		for(Enumeration e = ((DefaultMutableTreeNode) model.getRoot()).breadthFirstEnumeration(); e.hasMoreElements();){
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) e.nextElement();
			
			// TODO: Get the parent album of newAlbum
			ADD_YOUR_ALBUM_TYPE parentAlbum; 
			//parentAlbum = newAlbum.getParentAlbum();
			
			
			if(parentAlbum.equals(parent.getUserObject())){
				
				DefaultMutableTreeNode trnode = new DefaultMutableTreeNode();
				trnode.setUserObject(newAlbum);
				
				model.insertNodeInto(trnode, parent,
						parent.getChildCount());
				albumTree.scrollPathToVisible(new TreePath(trnode.getPath()));
				
			}
		}
	}
	
	/**
	 * Updates the album hierarchy by removing an album from it
	 */
	public void onAlbumRemoved(ADD_YOUR_ALBUM_TYPE album){
		assert album != null;
		
		DefaultTreeModel model = (DefaultTreeModel) albumTree.getModel();
		
		//We search for the parent node so we update the tree as intended
		for(Enumeration e = ((DefaultMutableTreeNode) model.getRoot()).breadthFirstEnumeration(); e.hasMoreElements();){
			DefaultMutableTreeNode current = (DefaultMutableTreeNode) e.nextElement();
			if(album.equals(current.getUserObject())){
				if(current != null){
					model.removeNodeFromParent(current);
				}
			}
		}
	}
	
	/**
	 * When called, the contents of the selected album are displayed in the clipTable
	 * 
	 */
	public void onClipsUpdated(){
		ADD_YOUR_ALBUM_TYPE a = (ADD_YOUR_ALBUM_TYPE) getSelectedTreeNode().getUserObject();
		clipTable.display(a);
	}
}
