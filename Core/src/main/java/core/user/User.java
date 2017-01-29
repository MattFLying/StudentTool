package core.user;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -200636714946439691L;
	private String login, password;
	private Role role;
	private Activity activity;
	private Integer enabled;
	
	public enum Role {
		ADMIN("ROLE_ADMIN"), STUDENT("ROLE_STUDENT"), TEACHER("ROLE_TEACHER");
		
		
		private String name;		
		Role(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	} 
	public enum Activity {
		ENABLED("Aktywne", 1), DISABLED("Nie aktywne", 0);
		
		
		private String name;
		private int activity;
		Activity(String name, int activity) {
			this.name = name;
			this.activity = activity;
		}
		
		public String getName() {
			return name;
		}
		public int getActivity() {
			return activity;
		}
	} 
	
	
	public User() {
		this.login = null;
		this.password = null;
		this.enabled = null;
	}
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.enabled = null;
	}
	public User(String login, String password, Integer enabled) {
		this.login = login;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setRole(String role) {
		if(role.equals(Role.ADMIN.getName())) {
			this.role = Role.ADMIN;
		} else if(role.equals(Role.STUDENT.getName())) {
			this.role = Role.STUDENT;
		} else if(role.equals(Role.TEACHER.getName())) {
			this.role = Role.TEACHER;
		}	
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
		setActivity(enabled);
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public void setActivity(String activity) {
		if(activity.equals(Activity.ENABLED.getName())) 
			this.activity = Activity.ENABLED;
		if(activity.equals(Activity.DISABLED.getName())) 
			this.activity = Activity.DISABLED;
	}
	public void setActivity(Integer activity) {
		if(activity.equals(Activity.ENABLED.getActivity())) 
			this.activity = Activity.ENABLED;
		if(activity.equals(Activity.DISABLED.getActivity())) 
			this.activity = Activity.DISABLED;
	}
}