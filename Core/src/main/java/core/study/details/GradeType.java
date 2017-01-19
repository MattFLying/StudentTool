package core.study.details;

public enum GradeType {
	NORMAL("Zwyk³a"), SEMESTRAL("Koñcowa");
	
	
	private String name;
	GradeType(String type) {
		this.name = type;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}