package core.study.details;

public enum CourseForm {
	LECTURE("Wyklad", 0), LABORATORY("Labolatorium", 1), PROJECT("Projekt", 2), EXERCISES("Cwiczenia", 3);
	
	
	private String name;
	private int id;
	
	CourseForm(String name, int id) {
		this.name= name;
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
}