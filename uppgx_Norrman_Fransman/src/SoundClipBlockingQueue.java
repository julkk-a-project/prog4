import java.util.LinkedList;
import java.util.List;

public class SoundClipBlockingQueue {

	private List<SoundClip> queue = new LinkedList<SoundClip>();
	
	// Add a SoundClip to the queue, and notify all
	// threads waiting for SoundClips on the queue
	public synchronized void enqueue(SoundClip item){
		queue.add(item);
	    notifyAll();
	}
	
	// Remove a SoundClip from the queue. 
	// If the queue is empty, the invoking thread will wait until
	// there is a SoundClip in the queue, and then remove it from
	// the queue.
	public synchronized SoundClip dequeue() throws InterruptedException{
		while(queue.size() == 0){
			wait();
		}
	    return queue.remove(0);
	}
}
