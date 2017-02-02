package core.humanity.details;

/***
 * Enumerate type represents research titles.
 * 
 * @author Mateusz Mucha
 *
 */
public enum ResearchTitle {
	NONE("brak"), LIC("lic."), INZ("in¿."), MGR("mgr."), DR("dr"), INZ_MGR("mgr in¿."), DR_INZ("dr in¿."), DR_HAB(
			"dr hab."), DR_INZ_HAB("dr hab. in¿."), PROF(
					"prof."), PROF_DR("prof. dr"), PROF_DR_HAB("prof. dr hab."), PROF_DR_HAB_INZ("prof. dr hab. in¿.");

	private String name;

	/***
	 * Constructor sets name value for all enumerate types.
	 * 
	 * @param name
	 */
	ResearchTitle(String name) {
		this.name = name;
	}

	/***
	 * Method gets name of enumerate type.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
}