package core.humanity.details;

public enum ResearchTitle {
	NONE("brak"), LICENTIATE("lic."), ENGINEER("in¿."), MAGISTER("mgr.");
	
	
	private String name;
	
	ResearchTitle(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
}