package core.model.base;

/***
 * Abstract class for create details of specific object. Should be extended by
 * all details classes.
 * 
 * @author Mateusz Mucha
 *
 */
public abstract class Details {
	private int id = 0;

	/***
	 * Default constructor sets basic fields.
	 */
	protected Details() {
		this.initialize();
	}

	/***
	 * Method gets identificator of specific object.
	 * 
	 * @return identificator
	 */
	public int getId() {
		return id;
	}

	/***
	 * Method sets identificator for specific object.
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/***
	 * Abstract method to override in all details object with own
	 * implementations depends than this class.
	 */
	protected abstract void initialize();
}