///*
//
//import java.util.ArrayList;
//
//public class MusicOrganizer {
//
//	Album root;
//	
//	
//	
//	public MusicOrganizer() { //counstructor, gör ett rotalbum
//		
//		 root = new Album("All Sound Files");
//		
//	}
//	
//	
//	
//	public Album findAlbum(ArrayList<Album> albums, String name){ //startar från roten och går neråt rekursivt tills rätt album hittas
//			
//		//Precondition
//		assert (root != null): "No albums available";
//		
//		try {
//		
//			for(int i = 0; i < albums.size(); i++) {
//			
//			if(albums.get(i).getName().equals(name))
//				return albums.get(i);
//			
//		}
//		
//		for(int i = 0; i < albums.size(); i++) {
//			
//			if(albums.get(i).getSubAlbums().size() != 0)
//				return findAlbum(albums.get(i).getSubAlbums(), name);
//			
//		}
//		
//		} catch(Exception e) {
//			
//			System.out.println(e);
//		
//		}
//		
//		return root;
//		
//		
//	}
//	
//	/*
//	 *söker albummet du vill lägga till ett subalbum på och 
//	 *callar metoden addSubAlbum() på det albummet.
//	 */
//	
//	public void addAlbum(String parentAlbumName, String newAlbumName) { 
//		
//		//Precondition
//		assert(root != null): "No parent available";
//		
//		Album parent;
//		
//		if (parentAlbumName.equals(root.getName()))
//			parent = root;
//		else
//			parent = findAlbum(root.getSubAlbums(), parentAlbumName);
//		
//		//Postcondition
//		assert(parent.addSubAlbum(new Album(parent, newAlbumName))):"No album added";
//		
//	}
//	
//	
//	/*
//	 *Söker föräldern till det albummet du vill ta bort och 
//	 *callar metoden removeSubAlbum på det albummet.
//	 */
//	
//	public void removeAlbum(String albumName) {
//		
//		//Preconditions
//		assert (root != null): "No albums available";
//		assert (!albumName.equals(root.getName())): "Root cannot be removed";
//		
//		Album albumToBeRemoved = findAlbum(root.getSubAlbums(), albumName);
//		
//		albumToBeRemoved.getParent().removeSubAlbum(albumToBeRemoved);
//		
//		while (albumToBeRemoved.getSubAlbums() == null) {
//			
//			for(int i = 0; i < albumToBeRemoved.getSubAlbums().size(); i++) {
//				
//				//Postcondition
//				assert(albumToBeRemoved.getSubAlbums().get(i).getParent().removeSubAlbum(albumToBeRemoved.getSubAlbums().get(i))): "No album removed";
//				
//			}
//			
//		}
//		
//		
//		
//	}	
//	
//
//}
//*/