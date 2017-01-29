package core.user;

import java.io.Serializable;

/***
 * Class represents user account.
 * 
 * @author Mateusz Mucha
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = -200636714946439691L;
	private String login, password;
	private Role role;
	private Activity activity;
	private Integer enabled;

	/***
	 * Enumerate type represents user account role type.
	 * 
	 * @author Mateusz Mucha
	 *
	 */
	public enum Role {
		ADMIN("ROLE_ADMIN"), STUDENT("ROLE_STUDENT"), TEACHER("ROLE_TEACHER");

		private String name;

		/***
		 * Constructor sets name of role.
		 * 
		 * @param name
		 *            - name of role
		 */
		Role(String name) {
			this.name = name;
		}

		/***
		 * Method gets name of role.
		 * 
		 * @return name of role
		 */
		public String getName() {
			return name;
		}
	}

	/***
	 * Enumerate type represents activity of user.
	 * 
	 * @author Mateusz Mucha
	 *
	 */
	public enum Activity {
		ENABLED("Aktywne", 1), DISABLED("Nie aktywne", 0);

		private String name;
		private int activity;

		/***
		 * Constructor sets name and value of activity.
		 * 
		 * @param name
		 *            - name of activity
		 * @param activity
		 *            - value of activity
		 */
		Activity(String name, int activity) {
			this.name = name;
			this.activity = activity;
		}

		/***
		 * Method gets name of activity.
		 * 
		 * @return name of activity
		 */
		public String getName() {
			return name;
		}

		/***
		 * Method gets activity value.
		 * 
		 * @return activity value
		 */
		public int getActivity() {
			return activity;
		}
	}

	/***
	 * Default constructor sets basic fields.
	 */
	public User() {
		this.login = null;
		this.password = null;
		this.enabled = null;
	}

	/***
	 * Default constructor sets basic field as login and password.
	 * 
	 * @param login
	 *            - user login
	 * @param password
	 *            - user password
	 */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.enabled = null;
	}

	/***
	 * Default constructor sets all basic fields.
	 * 
	 * @param login
	 *            - user login
	 * @param password
	 *            - user password
	 * @param enabled
	 *            - user activity
	 */
	public User(String login, String password, Integer enabled) {
		this.login = login;
		this.password = password;
		this.enabled = enabled;
	}

	/***
	 * Method gets user login.
	 * 
	 * @return user login
	 */
	public String getLogin() {
		return login;
	}

	/***
	 * Method sets user login.
	 * 
	 * @param login
	 *            - user login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/***
	 * Method gets user password.
	 * 
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/***
	 * Method sets user password.
	 * 
	 * @param password
	 *            - user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/***
	 * Method gets user role type.
	 * 
	 * @return user role type
	 */
	public Role getRole() {
		return role;
	}

	/***
	 * Method sets user role type.
	 * 
	 * @param role
	 *            - user role type
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/***
	 * Method sets user role type.
	 * 
	 * @param role
	 *            - user role type as string value
	 */
	public void setRole(String role) {
		if (role.equals(Role.ADMIN.getName()))
			this.role = Role.ADMIN;
		if (role.equals(Role.STUDENT.getName()))
			this.role = Role.STUDENT;
		if (role.equals(Role.TEACHER.getName()))
			this.role = Role.TEACHER;
	}

	/***
	 * Method gets user activity value.
	 * 
	 * @return user activity value
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/***
	 * Method sets user activity value.
	 * 
	 * @param enabled
	 *            - user activity value
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
		setActivity(enabled);
	}

	/***
	 * Method gets user activity.
	 * 
	 * @return user activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/***
	 * Method sets user activity.
	 * 
	 * @param activity
	 *            - user activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/***
	 * Method sets user activity.
	 * 
	 * @param activity
	 *            - user activity as string value
	 */
	public void setActivity(String activity) {
		if (activity.equals(Activity.ENABLED.getName()))
			this.activity = Activity.ENABLED;
		if (activity.equals(Activity.DISABLED.getName()))
			this.activity = Activity.DISABLED;
	}

	/***
	 * Method sets user activity.
	 * 
	 * @param activity
	 *            - user activity as integer value
	 */
	public void setActivity(Integer activity) {
		if (activity.equals(Activity.ENABLED.getActivity()))
			this.activity = Activity.ENABLED;
		if (activity.equals(Activity.DISABLED.getActivity()))
			this.activity = Activity.DISABLED;
	}
}