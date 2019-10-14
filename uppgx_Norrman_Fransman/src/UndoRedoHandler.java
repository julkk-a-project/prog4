import java.util.ArrayList;

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


/**
 * copypasted from a dead-end branch and modified to fit our needs.
 * 
 * code duplication is for sissies!
 * 
 * @author julkkis666
 *
 */
public class UndoRedoHandler {





	static ArrayList<Album> getAllChildren(Album album){
		ArrayList<ArrayList<Album>> levelList = new ArrayList<ArrayList<Album>>();

		
		//workaround to make copypaste work with simple album and not arraylist album
		ArrayList<Album> album1 = new ArrayList<Album>();
		album1.add(album);
		
		levelList.add(0,album1);

		if(album.getSubAlbums().size() > 0) {
			levelList = getAllChildrenRec(levelList, album.getSubAlbums(), 0);
		}
		return parseSubLists(levelList);
	}

	/**
	 * parse ArrayList<ArrayList<Album>> to ArrayList<Album>
	 * @param levelList
	 * @return
	 */
	private static ArrayList<Album> parseSubLists(ArrayList<ArrayList<Album>> levelList) {

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
	private static ArrayList<ArrayList<Album>> getAllChildrenRec(ArrayList<ArrayList<Album>> levelList, ArrayList<Album> subAlbums, int level){
		for(int i = 0; i < subAlbums.size(); i++) {
			Album curAlbum = subAlbums.get(i);
			levelList.get(level).add(curAlbum);
			if(curAlbum.getSubAlbums().size() > 0) {
				levelList = getAllChildrenRec(levelList, curAlbum.getSubAlbums(), level++);	
			}
			
		}
		return levelList;
	}
}