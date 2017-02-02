package core.model.base.humanity;

import core.humanity.details.PersonalDetails;
import core.model.base.IGenericModel;

/***
 * Basic interface for all human types.
 * 
 * @author Mateusz Mucha
 *
 * @param <BaseModel>
 *            - this is a basic model type represent some human
 * @param <ModelDetails>
 *            - this is a specific details object type
 */
public interface IGenericPerson<BaseModel extends Person, ModelDetails extends PersonalDetails>
		extends IGenericModel<ModelDetails> {
	/***
	 * Method to gets human type.
	 * 
	 * @return base model type
	 */
	BaseModel getModel();

	/***
	 * Method to sets basic model type of human type.
	 * 
	 * @param model
	 *            - specific human type
	 */
	void setModel(BaseModel model);
}