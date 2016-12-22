package model.human.student;

import java.util.Date;
import model.bank.BankAccount;
import model.data.details.Details;
import model.data.details.enums.FormOfStudy;
import model.data.details.enums.StudySystem;
import model.department.Department;
import model.fieldofstudy.FieldOfStudy;
import model.group.Group;
import model.specialization.Specialization;

public class StudentDetails extends Details {
	private Long albumNumber;
	private Date startStudyDate, endStudyDate;
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
		this.albumNumber = null;
		this.startStudyDate = null;
		this.endStudyDate = null;
		this.department = null;
		this.fieldOfStudy = null;
		this.group = null;
		this.termTitle = null;
		this.specialization = null;
		this.diplomaTitle = null;
		this.currentTermNumber = null;
		this.formOfStudy = null;
		this.studySystem = null;
		this.bankNumber = null;
	}
	
	
	public Long getAlbumNumber() {
		return albumNumber;
	}
	public void setAlbumNumber(Long albumNumber) {
		this.albumNumber = albumNumber;
	}
	public Date getStartStudyDate() {
		return startStudyDate;
	}
	public void setStartStudyDate(Date startStudyDate) {
		this.startStudyDate = startStudyDate;
	}
	public Date getEndStudyDate() {
		return endStudyDate;
	}
	public void setEndStudyDate(Date endStudyDate) {
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