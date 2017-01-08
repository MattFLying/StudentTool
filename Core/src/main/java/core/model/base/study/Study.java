package core.model.base.study;

import core.study.details.StudyDetails;

public abstract class Study<BaseModel extends Study, ModelDetails extends StudyDetails> implements IGenericStudy<BaseModel, ModelDetails> {
	private ModelDetails details;
	
	
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