
public interface Subject {

	public void register(Soundclipsobserver o);
	public void unregister(Soundclipsobserver o);
	public void notifyObserver();

	
}
