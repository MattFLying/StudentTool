package core.user;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -200636714946439691L;
	private String login, password;
	private Role role;
	
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
	
	
	public User() {
		this.login = null;
		this.password = null;
	}
	public User(String login, String password) {
		this.login = login;
		this.password = password;
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
}