package model.human.details;

import java.text.SimpleDateFormat;

public abstract class Details {
	private int id = 0;
	protected static final SimpleDateFormat DATE_PATTERN = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}