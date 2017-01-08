package core.model.base;

public interface IGenericModel<ModelDetails extends Details> {
	ModelDetails getDetails();
	void setDetails(ModelDetails details);
}