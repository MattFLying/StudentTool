package core.model.base.study;

import core.model.base.IGenericModel;
import core.study.details.StudyDetails;

public interface IGenericStudy<BaseModel extends Study, ModelDetails extends StudyDetails> extends IGenericModel<ModelDetails> {
	BaseModel getModel();
	void setModel(BaseModel model);
}