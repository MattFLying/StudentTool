package core.study.details;

/***
 * Enumerate type represents form of study.
 * 
 * @author Mateusz Mucha
 *
 */
public enum FormOfStudy {
	NONE("brak."), FIRST_CYCLE_LICENTIATE("Licencjat"), FIRST_CYCLE_ENGINEER("In¿ynier"), SECOND_CYCLE_MAGISTER(
			"Magister");

	private String name;

	/***
	 * Constructor sets name of forms of study.
	 * 
	 * @param name
	 *            - name of form of study
	 */
	FormOfStudy(String name) {
		this.name = name;
	}

	/***
	 * Method to gets form of study name.
	 * 
	 * @return form of study name
	 */
	public String getName() {
		return name;
	}
}