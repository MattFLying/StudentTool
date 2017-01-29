package core.study.fieldofstudy;

import core.study.details.StudyDetails;

/***
 * Class represents specialization details.
 * 
 * @author Mateusz Mucha
 *
 */
public class SpecializationDetails extends StudyDetails {
	private String specializationName;
	private FieldOfStudy fieldOfStudy;

	/***
	 * Default constructor sets basic fields.
	 */
	public SpecializationDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.specializationName = null;
		this.fieldOfStudy = new FieldOfStudy();
	}

	/***
	 * Method to gets specialization name.
	 * 
	 * @return specialization name
	 */
	public String getSpecializationName() {
		return specializationName;
	}

	/***
	 * Method to sets specialization name.
	 * 
	 * @param specializationName
	 *            - specialization name
	 */
	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName.replaceAll("­", "").replaceAll(",", "");
	}

	/***
	 * Method to gets field of study for specialization.
	 * 
	 * @return field of study for specialization
	 */
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}

	/***
	 * Method to sets field of study for specialization.
	 * 
	 * @param fieldOfStudy
	 *            - field of study for specialization
	 */
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
}