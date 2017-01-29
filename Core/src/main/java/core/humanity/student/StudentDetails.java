package core.humanity.student;

import core.humanity.details.PersonalDetails;
import core.study.department.Department;
import core.study.details.FormOfStudy;
import core.study.details.StudySystem;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.group.Group;

/***
 * Class represents all data of student on univeristy.
 * 
 * @author Mateusz Mucha
 *
 */
public class StudentDetails extends PersonalDetails {
	private Long albumNumber;
	private String startStudyDate, endStudyDate, bankNumber, email;
	private Integer currentTermNumber;
	private FormOfStudy formOfStudy;
	private StudySystem studySystem;
	private Specialization specialization;
	private Department department;
	private FieldOfStudy fieldOfStudy;
	private Group group;

	/**
	 * Default constructor sets basic fields.
	 */
	public StudentDetails() {
		super();
	}

	@Override
	protected void initialize() {
		super.initialize();
		this.albumNumber = null;
		this.startStudyDate = null;
		this.endStudyDate = null;
		this.department = new Department();
		this.fieldOfStudy = new FieldOfStudy();
		this.group = new Group();
		this.specialization = new Specialization();
		this.currentTermNumber = null;
		this.formOfStudy = null;
		this.studySystem = null;
		this.bankNumber = null;
		this.email = null;
	}

	/***
	 * Method to gets album number of student.
	 * 
	 * @return album number
	 */
	public Long getAlbumNumber() {
		return albumNumber;
	}

	/***
	 * Method to sets album number of student.
	 * 
	 * @param albumNumber
	 *            - album number of student
	 */
	public void setAlbumNumber(Long albumNumber) {
		this.albumNumber = albumNumber;
	}

	/***
	 * Method to gets date of beginning of study.
	 * 
	 * @return date of beginning of study
	 */
	public String getStartStudyDate() {
		return startStudyDate;
	}

	/***
	 * Method to sets date of beginning of study.
	 * 
	 * @param startStudyDate
	 *            - date of beginning of study
	 */
	public void setStartStudyDate(String startStudyDate) {
		this.startStudyDate = startStudyDate;
	}

	/***
	 * Method to gets date of end of study.
	 * 
	 * @return date of end of study
	 */
	public String getEndStudyDate() {
		return endStudyDate;
	}

	/***
	 * Method to sets date of end of study.
	 * 
	 * @param endStudyDate
	 *            - date of end of study
	 */
	public void setEndStudyDate(String endStudyDate) {
		this.endStudyDate = endStudyDate;
	}

	/***
	 * Method to gets department of student.
	 * 
	 * @return department
	 */
	public Department getDepartment() {
		return department;
	}

	/***
	 * Method to sets department of student.
	 * 
	 * @param department
	 *            - department
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/***
	 * Method to gets field of study of student.
	 * 
	 * @return field of study
	 */
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}

	/***
	 * Method to sets field of study of student.
	 * 
	 * @param fieldOfStudy
	 *            - field of study
	 */
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	/***
	 * Method to gets group of student.
	 * 
	 * @return group of student
	 */
	public Group getGroup() {
		return group;
	}

	/***
	 * Method to sets group of student.
	 * 
	 * @param group
	 *            - group of student
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/***
	 * Method to gets specialization of student.
	 * 
	 * @return specialization of student
	 */
	public Specialization getSpecialization() {
		return specialization;
	}

	/***
	 * Method to sets specialization of student.
	 * 
	 * @param specialization
	 *            - specialization of student
	 */
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	/***
	 * Method to gets number of current term of student.
	 * 
	 * @return number of current term
	 */
	public Integer getCurrentTermNumber() {
		return currentTermNumber;
	}

	/***
	 * Method to sets number of current term of student.
	 * 
	 * @param currentTermNumber
	 *            - number of current student term
	 */
	public void setCurrentTermNumber(Integer currentTermNumber) {
		this.currentTermNumber = currentTermNumber;
	}

	/***
	 * Method to gets current form of study of student.
	 * 
	 * @return form of study of student
	 */
	public FormOfStudy getFormOfStudy() {
		return formOfStudy;
	}

	/***
	 * Method to sets current form of study of student.
	 * 
	 * @param formOfStudy
	 *            - form of study of student
	 */
	public void setFormOfStudy(FormOfStudy formOfStudy) {
		this.formOfStudy = formOfStudy;
	}

	/***
	 * Method to sets current form of study of student.
	 * 
	 * @param formOfStudy
	 *            - form of study of student as string value
	 */
	public void setFormOfStudy(String formOfStudy) {
		if (formOfStudy.equals(FormOfStudy.NONE.getName()))
			this.formOfStudy = FormOfStudy.NONE;
		if (formOfStudy.equals(FormOfStudy.FIRST_CYCLE_ENGINEER.getName()))
			this.formOfStudy = FormOfStudy.FIRST_CYCLE_ENGINEER;
		if (formOfStudy.equals(FormOfStudy.FIRST_CYCLE_LICENTIATE.getName()))
			this.formOfStudy = FormOfStudy.FIRST_CYCLE_LICENTIATE;
		if (formOfStudy.equals(FormOfStudy.SECOND_CYCLE_MAGISTER.getName()))
			this.formOfStudy = FormOfStudy.SECOND_CYCLE_MAGISTER;
	}

	/***
	 * Method to gets current study system of student.
	 * 
	 * @return study system of student
	 */
	public StudySystem getStudySystem() {
		return studySystem;
	}

	/***
	 * Method to sets current study system of student.
	 * 
	 * @param studySystem
	 *            - study system of student
	 */
	public void setStudySystem(StudySystem studySystem) {
		this.studySystem = studySystem;
	}

	/***
	 * Method to sets current study system of student.
	 * 
	 * @param studySystem
	 *            - study system of student as string value
	 */
	public void setStudySystem(String studySystem) {
		if (studySystem.equals(StudySystem.FULL_TIME.getName()))
			this.studySystem = StudySystem.FULL_TIME;
		if (studySystem.equals(StudySystem.HALF_TIME.getName()))
			this.studySystem = StudySystem.HALF_TIME;
		if (studySystem.equals(StudySystem.NONE.getName()))
			this.studySystem = StudySystem.NONE;
	}

	/***
	 * Method to gets ban number of student.
	 * 
	 * @return bank number
	 */
	public String getBankNumber() {
		return bankNumber;
	}

	/***
	 * Method to sets bank number of student.
	 * 
	 * @param bankNumber
	 *            - bank number
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	/***
	 * Method to gets email address of student.
	 * 
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/***
	 * Method to sets email address of student.
	 * 
	 * @param email
	 *            - email address of student
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}