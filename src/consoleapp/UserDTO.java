package consoleapp;

import java.util.ArrayList;

public class UserDTO {

	private String name;
	private String email;
	private ArrayList<String> phone;

	public UserDTO(String name, String email, ArrayList<String> phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getPhone() {
		return phone;
	}

	public void setPhone(String oldPhone, String newPhone) {
		for (String string : this.phone) {
			if (string.equalsIgnoreCase(oldPhone)) {
				this.phone.remove(oldPhone);
				this.phone.add(newPhone);
				break;
			};
		}

	}

}
