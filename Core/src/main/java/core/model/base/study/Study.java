package core.model.base.study;

import core.study.details.StudyDetails;

/***
 * Basic abstract class represents study system object should be extended by
 * each study system object.
 * 
 * @author Mateusz Mucha
 *
 * @param <BaseModel>
 *            - this is a basic model type represent some study object
 * @param <ModelDetails>
 *            - this is a specific details object type
 */
public abstract class Study<BaseModel extends Study, ModelDetails extends StudyDetails>
		implements IGenericStudy<BaseModel, ModelDetails> {
	private ModelDetails details;

	/***
	 * Default constructor sets basic field for type details.
	 * 
	 * @param details
	 *            - details type of object
	 */
	public Study(ModelDetails details) {
		this.details = details;
	}

	public ModelDetails getDetails() {
		return details;
	}

	public void setDetails(ModelDetails details) {
		this.details = details;
	}
}