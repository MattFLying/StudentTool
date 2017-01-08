package core.model.base;

public abstract class Details {
	private int id = 0;
	
	
	protected Details() {
		this.initialize();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	protected abstract void initialize();
}