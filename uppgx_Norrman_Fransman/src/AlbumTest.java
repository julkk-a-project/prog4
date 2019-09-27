

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;



//TODO: (@Fransman) chek if these tests are according to the feedback we got from previous inlämning 


//class AlbumTest {
	
	//String root = "All Sound Files";
	
	/*
	 * Addar olika mängder album och kollar att add funktionen ändå funkar 
	 * mha av att söka elementen efter man lagt till dem.
	 */
	
//	@Test
//	void addAlbumsTest() {
//		
//		MusicOrganizer test = new MusicOrganizer();
//		
//		assertEquals("All Sound Files", test.findAlbum(test.root.getSubAlbums(), "All Sound Files").getName());
//		
//		test.addAlbum(root, "Music"); 
//		
//		assertEquals("Music", test.findAlbum(test.root.getSubAlbums(), "Music").getName());
//		
//		test.addAlbum("Music", "Classical Music");
//		test.addAlbum("Classical Music", "Beethoven");
//		test.addAlbum(root, "Rap");
//		test.addAlbum(root, "Disney");
//		test.addAlbum("Disney", "Lion King");
//		test.addAlbum("Lion King", "Hakuna Matata");
//		test.addAlbum("Rap", "Eminem");
//		test.addAlbum("Eminem", "Rap God");
//		test.addAlbum("Eminem", "Mammas Spaghetti");
//		test.addAlbum("Eminem", "Stan");
//		test.addAlbum("Rap", "Ice Cube");
//		test.addAlbum("Ice Cube", "F**k the police");
//	
//		
//		assertEquals("Beethoven", test.findAlbum(test.root.getSubAlbums(), "Beethoven").getName());
//		assertEquals("Rap", test.findAlbum(test.root.getSubAlbums(), "Rap").getName());
//		assertEquals("Mammas Spaghetti", test.findAlbum(test.root.getSubAlbums(), "Mammas Spaghetti").getName());
//		assertEquals("Hakuna Matata", test.findAlbum(test.root.getSubAlbums(), "Hakuna Matata").getName());
//
//	}
	
	/*
	 *Addar album och kollar att man kan ta bort dem och att även subalbummen
	 *går bort när man radera deras förälder. 
	 */
	
//	@Test
//	void removeAlbumsTest() {
//		
//		MusicOrganizer test = new MusicOrganizer();
//		
//		test.addAlbum(root, "Music");
//		test.addAlbum("Music", "Classical Music");
//		test.addAlbum("Classical Music", "Beethoven");
//		test.addAlbum(root, "Rap");
//		test.addAlbum(root, "Disney");
//		test.addAlbum("Disney", "Lion King");
//		test.addAlbum("Lion King", "Hakuna Matata");
//		
//		assertEquals("Music", test.findAlbum(test.root.getSubAlbums(), "Music").getName());
//		test.removeAlbum("Music");
//		assertEquals(test.root.getName(), test.findAlbum(test.root.getSubAlbums(), "Beethoven").getName());
//		
//		assertEquals("Hakuna Matata", test.findAlbum(test.root.getSubAlbums(), "Hakuna Matata").getName());
//		test.removeAlbum("Hakuna Matata");
//		assertEquals(test.root.getName(), test.findAlbum(test.root.getSubAlbums(), "Hakuna Matata").getName());
//		
//		assertEquals("Rap", test.findAlbum(test.root.getSubAlbums(), "Rap").getName());
//	
//		test.removeAlbum("Rap");
//		test.removeAlbum("Disney");
//		
//		assertEquals(test.root.getName(), test.findAlbum(test.root.getSubAlbums(), "Music").getName());
//		assertEquals(test.root.getName(), test.findAlbum(test.root.getSubAlbums(), "Lion King").getName());
//		assertEquals(test.root.getName(), test.findAlbum(test.root.getSubAlbums(), "Rap").getName());
//		
//	}
	
	/*
	 * Lägger till ljudklipp och kollar att dom existerar
	 */
//	@Test
//	void addSoundClips() {
//		
//		MusicOrganizer test = new MusicOrganizer();
//		File testFile = new File("file1");
//		SoundClip soundClip = new SoundClip(testFile);
//		
//		test.addAlbum(root, "Music");
//		Album testAlbum = test.findAlbum(test.root.getSubAlbums(), "Music");
//		testAlbum.addSoundClip(soundClip.getFile());
//		
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(0).toString());
//		
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(1).toString());
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(2).toString());
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(3).toString());
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(4).toString());
//		
//	}
	
	/*
	 * lägger till ljudklipp och tar sedan bort några och kollar varefter att list.Size()
	 * stämmer överens med antalet element där borde finnas.
	 */
//	@Test
//	void removeSoundClips() {
//		
//		MusicOrganizer test = new MusicOrganizer();
//		File testFile = new File("testfile1.txt"); //fixed this so it's an actual file. TODO: irl it should be a .mp3 or what ever... could just be "/soundFiles/file.mp3" or what ever tbh.
//		SoundClip soundClip = new SoundClip(testFile);
//		
//		test.addAlbum(root, "Music");
//		Album testAlbum = test.findAlbum(test.root.getSubAlbums(), "Music");
//		
//		testAlbum.addSoundClip(soundClip.getFile());
//		
//		assertEquals(1, testAlbum.getSoundClipsSize());
//		
//		assertEquals(soundClip.getFile().toString(), test.findAlbum(test.root.getSubAlbums(), "Music").getSoundClip(0).toString());
//			
//		testAlbum.removeSoundClip(soundClip);
//		
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		testAlbum.addSoundClip(soundClip.getFile());
//		
//		testAlbum.removeSoundClip(soundClip);
//		testAlbum.removeSoundClip(soundClip);
//		
//		assertEquals(2, testAlbum.getSoundClipsSize());
//		
//		testAlbum.removeSoundClip(soundClip);
//		testAlbum.removeSoundClip(soundClip);
//		
//		assertEquals(0, testAlbum.getSoundClipsSize());
//		
//	}
		
//}
