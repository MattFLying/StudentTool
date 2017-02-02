package core.model.base.study;

import core.model.base.IGenericModel;
import core.study.details.StudyDetails;

/***
 * Basic interface represents all study system object.
 * 
 * @author Mateusz Mucha
 *
 * @param <BaseModel>
 *            - this is a basic model type represent some study object
 * @param <ModelDetails>
 *            - this is a specific details object type
 */
public interface IGenericStudy<BaseModel extends Study, ModelDetails extends StudyDetails>
		extends IGenericModel<ModelDetails> {
	/***
	 * Method to gets study type.
	 * 
	 * @return base model type
	 */
	BaseModel getModel();

	/***
	 * Method to sets basic model type of study type.
	 * 
	 * @param model
	 *            - specific study type
	 */
	void setModel(BaseModel model);
}