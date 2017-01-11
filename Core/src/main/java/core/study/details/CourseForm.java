package core.study.details;

public enum CourseForm {
	LECTURE("Wyklad"), LABORATORY("Labolatorium"), PROJECT("Projekt"), EXERCISES("Cwiczenia");
	
	
	private String name;
	
	CourseForm(String name) {
		this.name= name;
	}

	
	public String getName() {
		return name;
	}
}