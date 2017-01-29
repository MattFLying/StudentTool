package core.study.details;

/***
 * Enumerate type represents study system.
 * 
 * @author Mateusz Mucha
 *
 */
public enum StudySystem {
	NONE("brak"), FULL_TIME("Dzienne"), HALF_TIME("Zaoczne");

	private String name;

	StudySystem(String name) {
		this.name = name;
	}

	/***
	 * Method to gets name of study system.
	 * 
	 * @return name of study system
	 */
	public String getName() {
		return name;
	}
}