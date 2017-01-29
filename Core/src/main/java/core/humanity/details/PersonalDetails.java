package core.humanity.details;

import core.model.base.Details;

/***
 * Class represents personal details of human.
 * 
 * @author Mateusz Mucha
 *
 */
public class PersonalDetails extends Details {
	private String firstName, secondName, lastName, phoneNumber;
	private Long pesel;
	private String birthDate;
	private Address address;
	private ResearchTitle title;

	/***
	 * Default constructor sets basic fields.
	 */
	public PersonalDetails() {
		super();
	}

	@Override
	protected void initialize() {
		this.firstName = null;
		this.secondName = null;
		this.lastName = null;
		this.birthDate = null;
		this.pesel = null;
		this.address = new Address();
		this.phoneNumber = null;
		this.title = null;
	}

	/***
	 * Method gets first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/***
	 * Method sets first name.
	 * 
	 * @param firstName
	 *            - first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/***
	 * Method gets second name.
	 * 
	 * @return second name
	 */
	public String getSecondName() {
		return secondName;
	}

	/***
	 * Method sets second name.
	 * 
	 * @param secondName
	 *            - second name
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/***
	 * Method gets last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/***
	 * Method sets last name.
	 * 
	 * @param lastName
	 *            - last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/***
	 * Method gets birth date format DD-mm-YYYY.
	 * 
	 * @return birth date
	 */
	public String getBirthDate() {
		return this.birthDate;
	}

	/***
	 * Method sets birth date. Should be use format DD-mm-YYYY.
	 * 
	 * @param birthDate
	 *            - birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/***
	 * Method gets pesel number.
	 * 
	 * @return pesel number
	 */
	public Long getPesel() {
		return pesel;
	}

	/***
	 * Method sets pesel number
	 * 
	 * @param pesel
	 *            - pesel number
	 */
	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}

	/***
	 * Method gets address object.
	 * 
	 * @return address object
	 */
	public Address getAddress() {
		return address;
	}

	/***
	 * Method sets address object.
	 * 
	 * @param address
	 *            - address object
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/***
	 * Method gets phone number.
	 * 
	 * @return phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/***
	 * Method sets phone number.
	 * 
	 * @param phoneNumber
	 *            - phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/***
	 * Method gets research title.
	 * 
	 * @return research title
	 */
	public ResearchTitle getTitle() {
		return title;
	}

	/***
	 * Method sets research title.
	 * 
	 * @param title
	 *            - research title
	 */
	public void setTitle(ResearchTitle title) {
		this.title = title;
	}

	/***
	 * Method sets research title.
	 * 
	 * @param title
	 *            - research title as string name of title
	 */
	public void setTitle(String title) {
		if (title.equals(ResearchTitle.NONE.getName()))
			this.title = ResearchTitle.NONE;
		if (title.equals(ResearchTitle.LIC.getName()))
			this.title = ResearchTitle.LIC;
		if (title.equals(ResearchTitle.INZ.getName()))
			this.title = ResearchTitle.INZ;
		if (title.equals(ResearchTitle.MGR.getName()))
			this.title = ResearchTitle.MGR;
		if (title.equals(ResearchTitle.DR.getName()))
			this.title = ResearchTitle.DR;
		if (title.equals(ResearchTitle.INZ_MGR.getName()))
			this.title = ResearchTitle.INZ_MGR;
		if (title.equals(ResearchTitle.DR_INZ.getName()))
			this.title = ResearchTitle.DR_INZ;
		if (title.equals(ResearchTitle.DR_HAB.getName()))
			this.title = ResearchTitle.DR_HAB;
		if (title.equals(ResearchTitle.DR_INZ_HAB.getName()))
			this.title = ResearchTitle.DR_INZ_HAB;
		if (title.equals(ResearchTitle.PROF.getName()))
			this.title = ResearchTitle.PROF;
		if (title.equals(ResearchTitle.PROF_DR.getName()))
			this.title = ResearchTitle.PROF_DR;
		if (title.equals(ResearchTitle.PROF_DR_HAB.getName()))
			this.title = ResearchTitle.PROF_DR_HAB;
		if (title.equals(ResearchTitle.PROF_DR_HAB_INZ.getName()))
			this.title = ResearchTitle.PROF_DR_HAB_INZ;
	}
}