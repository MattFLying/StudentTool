package core.study.details;

public enum StudySystem {
	FULL_TIME("Dzienne"), HALF_TIME("Zaoczne");
	
	
	private String name;
	
	StudySystem(String name) {
		this.setName(name);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}