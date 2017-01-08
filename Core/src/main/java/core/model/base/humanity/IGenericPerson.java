package core.model.base.humanity;

import core.humanity.details.PersonalDetails;
import core.model.base.IGenericModel;

public interface IGenericPerson<BaseModel extends Person, ModelDetails extends PersonalDetails> extends IGenericModel<ModelDetails> {
	BaseModel getModel();
	void setModel(BaseModel model);
}