package sebdem.nouvis.datastructs;

public class Timer {

	public long initTime;
	public long startTime;
	public long endTime = 0;

	public Timer() {
		this(false);
	}

	public Timer(boolean startWithCreation) {
		initTime = System.nanoTime();
		
		if (startWithCreation)
			startTime = initTime;
	}

	public long start() {
		return (this.startTime = System.nanoTime());
	}

	public long get() {
		if (endTime != 0) {
			return (endTime - startTime);
		}
		return (System.nanoTime() - startTime);
	}

	public long end() {
		return (endTime = System.nanoTime());
	}
	
	public String toString(){
		return "Timer { initTime : " + initTime + ", startTime : " + startTime + ", endTime : " + endTime + " }";
	}

	
	public long asMilli(){
		return (this.get() / 1000000);
	}
	
	
}
