package core.study.details;

/***
 * Enumerate type represents form of courses.
 * 
 * @author Mateusz Mucha
 *
 */
public enum CourseForm {
	LECTURE("Wyk³ad", 0), LABORATORY("Labolatorium", 1), PROJECT("Projekt", 2), EXERCISES("Æwiczenia", 3);

	private String name;
	private int id;

	/***
	 * Constructor sets basic informations of course forms.
	 * 
	 * @param name
	 *            - name of course form
	 * @param id
	 *            - identificator of course form
	 */
	CourseForm(String name, int id) {
		this.name = name;
		this.id = id;
	}

	/***
	 * Method to gets name of course form.
	 * 
	 * @return name of course form
	 */
	public String getName() {
		return name;
	}

	/***
	 * Method to gets identificator of course form.
	 * 
	 * @return identificator of course form
	 */
	public int getId() {
		return id;
	}
}