package model.institute;

public class Institute implements IInstitute {
	private InstituteDetails instituteDetails;
	
	
	public Institute() {
		this.instituteDetails = new InstituteDetails();
	}

	
	public InstituteDetails getInstituteDetails() {
		return instituteDetails;
	}
	public void setInstituteDetails(InstituteDetails details) {
		this.instituteDetails = details;
	}
}