package model.human.details;

import java.util.Date;

public class StudentDetails extends Details {
	private Long albumNumber;
	private Date startStudyDate, endStudyDate;
	private String department, fieldOfStudy, group, typeOfStudy, termTitle, specialization, diplomaTitle;
	private Integer currentTermNumber;
	
	
	public StudentDetails() {
		this.albumNumber = null;
		this.startStudyDate = null;
		this.endStudyDate = null;
		this.department = null;
		this.fieldOfStudy = null;
		this.group = null;
		this.typeOfStudy = null;
		this.termTitle = null;
		this.specialization = null;
		this.diplomaTitle = null;
		this.currentTermNumber = null;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTypeOfStudy() {
		return typeOfStudy;
	}
	public void setTypeOfStudy(String typeOfStudy) {
		this.typeOfStudy = typeOfStudy;
	}
	public String getTermTitle() {
		return termTitle;
	}
	public void setTermTitle(String termTitle) {
		this.termTitle = termTitle;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
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
}