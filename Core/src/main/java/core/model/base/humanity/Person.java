package core.model.base.humanity;

import core.humanity.details.PersonalDetails;

/***
 * Abstract class represents human basic model which should be extended by each
 * human type.
 * 
 * @author Mateusz Mucha
 *
 * @param <BaseModel>
 *            - this is a basic model type represent some human should be
 *            extended by this Person type
 * @param <ModelDetails>
 *            - this is a specific details object type should be extended by
 *            personal details object
 */
public abstract class Person<BaseModel extends Person, ModelDetails extends PersonalDetails>
		implements IGenericPerson<BaseModel, ModelDetails> {
	private ModelDetails details;

	/***
	 * Default constructor sets basic field for type details.
	 * 
	 * @param details
	 *            - details type of object
	 */
	protected Person(ModelDetails details) {
		this.details = details;
	}

	public ModelDetails getDetails() {
		return details;
	}

	public void setDetails(ModelDetails details) {
		this.details = details;
	}
}