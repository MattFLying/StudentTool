package core.model.base;

/***
 * Basic generic interface using for all core objects.
 * 
 * @author Mateusz Mucha
 *
 * @param <ModelDetails>
 *            - this is a specific details object type
 */
public interface IGenericModel<ModelDetails extends Details> {
	/***
	 * Method gets specific details object type.
	 * 
	 * @return base model details type
	 */
	ModelDetails getDetails();

	/***
	 * Method sets details of specific object type.
	 * 
	 * @param details
	 *            - details type
	 */
	void setDetails(ModelDetails details);
}