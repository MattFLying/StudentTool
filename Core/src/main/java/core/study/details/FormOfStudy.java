package core.study.details;

public enum FormOfStudy {
	FIRST_CYCLE_LICENTIATE("Licencjat"), FIRST_CYCLE_ENGINEER("Inzynier"),
	SECOND_CYCLE_MAGISTER("Magister");
	
	
	private String name;
	
	FormOfStudy(String name) {
		this.setName(name);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}