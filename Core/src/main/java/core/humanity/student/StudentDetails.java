package core.humanity.student;

import java.util.Date;

import core.humanity.details.BankAccount;
import core.humanity.details.PersonalDetails;
import core.study.department.Department;
import core.study.details.FormOfStudy;
import core.study.details.StudySystem;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.group.Group;

public class StudentDetails extends PersonalDetails {
	private Long albumNumber;
	private String startStudyDate, endStudyDate;
	private String termTitle, diplomaTitle;
	private Integer currentTermNumber;
	private FormOfStudy formOfStudy;
	private StudySystem studySystem;
	private BankAccount bankNumber;
	private Specialization specialization;
	private Department department;
	private FieldOfStudy fieldOfStudy;
	private Group group;
	
	
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
		this.termTitle = null;
		this.specialization = new Specialization();
		this.diplomaTitle = null;
		this.currentTermNumber = null;
		this.formOfStudy = null;
		this.studySystem = null;
		this.bankNumber = new BankAccount();
	}
	public Long getAlbumNumber() {
		return albumNumber;
	}
	public void setAlbumNumber(Long albumNumber) {
		this.albumNumber = albumNumber;
	}
	public String getStartStudyDate() {
		return startStudyDate;
	}
	public void setStartStudyDate(String startStudyDate) {
		this.startStudyDate = startStudyDate;
	}
	public String getEndStudyDate() {
		return endStudyDate;
	}
	public void setEndStudyDate(String endStudyDate) {
		this.endStudyDate = endStudyDate;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getTermTitle() {
		return termTitle;
	}
	public void setTermTitle(String termTitle) {
		this.termTitle = termTitle;
	}
	public Specialization getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	public String getDiplomaTitle() {
		return diplomaTitle;
	}
	public void setDiplomaTitle(String diplomaTitle) {
		this.diplomaTitle = diplomaTitle;
	}
	public Integer getCurrentTermNumber() {
		return currentTermNumber;
	}
	public void setCurrentTermNumber(Integer currentTermNumber) {
		this.currentTermNumber = currentTermNumber;
	}
	public FormOfStudy getFormOfStudy() {
		return formOfStudy;
	}
	public void setFormOfStudy(FormOfStudy formOfStudy) {
		this.formOfStudy = formOfStudy;
	}
	public StudySystem getStudySystem() {
		return studySystem;
	}
	public void setStudySystem(StudySystem studySystem) {
		this.studySystem = studySystem;
	}
	public BankAccount getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(BankAccount bankNumber) {
		this.bankNumber = bankNumber;
	}
}