package core.model.base.humanity;

import core.humanity.details.PersonalDetails;

public abstract class Person<BaseModel extends Person, ModelDetails extends PersonalDetails> implements IGenericPerson<BaseModel, ModelDetails> {
	private ModelDetails details;
	
	
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