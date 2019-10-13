package undoRedo;

import java.util.ArrayList;
import java.util.List;

import musicPlayer.AbstractSoundObject;
import musicPlayer.Album;
import musicPlayer.MusicOrganizerController;



/*
 * we create a list of the tree that the removed album has under it,
 * so that we can then one by one add them in order of height
 * how the tree is supposed to look.
 * 
 * this is done with the "getAllChildren" method, and it's help method...
 * ...getAllChildrenRec.
 * 
 * they create an ArrayList<ArrayList<Album> which is then
 * parsed into an ArrayList<Album> so that we can add them
 * in the wanted order.
 * 
 * 
 *  Example:
 * 
 *   ___1___
 *  _2_   _3_
 *  4 5   6 7
 * 
 * ---> 1, 2, 3, 4, 5, 6, 7
 * 
 */



public class RemoveAlbum extends AbstractChange {

	public RemoveAlbum(MusicOrganizerController controller, List<AbstractSoundObject> soundObjects) {
		super(controller, soundObjects);
	}
	public RemoveAlbum(MusicOrganizerController controller, AbstractSoundObject soundObject) {
		super(controller, soundObject);
	}
	
	/**
	 * parse abstract to album
	 * @param subAlbums
	 * @return
	 */
	
	private ArrayList<Album> parseAbstract(List<AbstractSoundObject> abstractList){
		ArrayList<Album> parsed = new ArrayList<Album>();
		
		for (int i = 0; i < abstractList.size(); i++) {
			Album album = (Album)abstractList.get(i);
			parsed.add(album);
		}
		
		return parsed;
	}
	

	private ArrayList<Album> getAllChildren(List<AbstractSoundObject> subAlbums){
		ArrayList<ArrayList<Album>> levelList = new ArrayList<ArrayList<Album>>();
		
		levelList.add(0,parseAbstract(subAlbums));
		
		for( int i = 0;i < subAlbums.size(); i++ ) {
			Album curAlbum = (Album) subAlbums.get(i);
			levelList = getAllChildrenRec(levelList, curAlbum.getSubAlbums(), i);
		}
		return parseSubLists(levelList);
	}
	
	/**
	 * parse ArrayList<ArrayList<Album>> to ArrayList<Album>
	 * @param levelList
	 * @return
	 */
	private ArrayList<Album> parseSubLists(ArrayList<ArrayList<Album>> levelList) {
		
		ArrayList<Album> finalList = new ArrayList<Album>();
		
		for (int i = 0; i < levelList.size(); i++) {
			ArrayList<Album> level = levelList.get(i);
			finalList.addAll(level);
		}
		
		
		return finalList;
	}
	
	/**
	 * on level "level", .add(subAlbums), then call self on all subAlbums with level++. 
	 * @param levelList
	 * @param subAlbums
	 * @param level
	 * @return
	 */
	private ArrayList<ArrayList<Album>> getAllChildrenRec(ArrayList<ArrayList<Album>> levelList, ArrayList<Album> subAlbums, int level){
		for(int i = 0; i < subAlbums.size(); i++) {
			Album curAlbum = subAlbums.get(i);
			levelList.get(level).add(curAlbum);
			levelList = getAllChildrenRec(levelList, curAlbum.getSubAlbums(), level++);
		}
		return levelList;
	}
	
	
	
	
	@Override
	public void undo() {
		
		ArrayList<Album> list = getAllChildren(soundObjects);
		
		for(int i = 0; i < list.size(); i++) {
			Album current = list.get(i);
			controller.getView().onAlbumAdded(current, current.getParent());
		}	
	}

	@Override
	public void redo() {

		controller.getUndoRedoHandler().redoChange(new RemoveAlbum(controller, soundObjects));
		controller.getView().onAlbumRemoved(parseAbstract(soundObjects).get(0));

	}

}
