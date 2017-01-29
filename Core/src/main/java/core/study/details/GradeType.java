package core.study.details;

/***
 * Enumerate type represents types of grades.
 * 
 * @author Mateusz Mucha
 *
 */
public enum GradeType {
	NORMAL("Zwyk³a"), SEMESTRAL("Koñcowa"), EXAM("Egzamin");

	private String name;

	/***
	 * Constructor sets name of grade type.
	 * 
	 * @param type
	 */
	GradeType(String type) {
		this.name = type;
	}

	/***
	 * Method to get name of grade type.
	 * 
	 * @return name of grade type
	 */
	public String getName() {
		return name;
	}
}