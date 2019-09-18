import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClipPlayer implements Runnable {

	SoundClipBlockingQueue queue;
	private static final int SLEEP_DELAY = 1000; // Sleep time between sound clips when playing several in a row
	
	public SoundClipPlayer(SoundClipBlockingQueue queue){
		this.queue = queue;
	}
	
	// Main loop for the SoundClipPlayer thread
	// The thread will dequeue a SoundClip from the
	// SoundClipBlockingQueue and play it. If the queue
	// is empty, the thread will go to sleep until
	// there is a SoundClip to retrieve from the queue.
	@Override
	public void run(){
		SoundClip sc = null;
		while (true) {
			try {
				sc = (SoundClip) queue.dequeue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(sc != null)
				this.playClip(sc);
		}
	}
	
	/**
	 * Plays the WAV SoundClip given as argument
	 * @param sc - the SoundClip to play
	 */
	private void playClip(SoundClip sc){
		AudioInputStream audioIn = null;
		Clip clip = null;
		
		System.out.print("Now playing " + sc.getFile().getName() + ". ");
		
		try{
			audioIn = AudioSystem.getAudioInputStream(sc.getFile());
		} catch(IOException e) {
			System.out.println("IOException");
		} catch(UnsupportedAudioFileException e) {
			System.out.println("UnsupportedAudioFileException");
		}
		try{
			clip = AudioSystem.getClip();
		} catch(LineUnavailableException e) {
			System.out.println("LineUnavailableException");
		}
		try{
			clip.open(audioIn);
		} catch(IOException e) {
			System.out.println("IOException");
		} catch(LineUnavailableException e) {
			System.out.println("LineUnavailableException");
		}
		int len = (int) clip.getMicrosecondLength()/1000;
		System.out.println("Clip length " + len + " milliseconds.");
		clip.start();
		try{
			Thread.sleep(len + SLEEP_DELAY);
		} catch(InterruptedException e) {
			System.out.println("Thread was interrupted");
		}
	}

}
