package by.filippov.library.entety;

public class User implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum UserType {
		ADMIN, LIBRARIST, USER;
	}

	private String login;
	private String password;
	private UserType type;
	private String name;

	public User(String login, String password, UserType type, String name) {
		super();
		this.login = login;
		this.password = password;
		this.type = type;
		this.name = name;
	}

	public User(String login, String password, String type, String name) {
		super();
		this.login = login;
		this.password = password;
		this.type = UserType.valueOf(type.toUpperCase());
		this.name = name;
	}

	public User() {
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public void setType(String type) {
		this.type = UserType.valueOf(type.toUpperCase());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
