package skeleton;

public class Observer {
	private int id;
	private Subject subject;

	public Observer(int id) {
		this.id = id;
	}

	public int getID(){
		return id;
	}


	public void subscribe(Subject sub) {
		this.subject = sub;
	}


	public void unsubscribe() {
		subject.unregister(this);
	}
	
	public void update(){
		// TODO: The observer will exit the queue 
		// once the notification has value >= this.id+7.
		// Don't forget they will leave if it is their number too.
		int msg = Integer.parseInt(subject.getMessage());
		if (msg == this.id || msg >= this.id+7) {
			subject.unregister(this);
			if (msg == this.id) System.out.println("Your order is ready");
			if (msg >= this.id+7) System.out.println("You waited too long");
		}
		else return;
		
	}
}
