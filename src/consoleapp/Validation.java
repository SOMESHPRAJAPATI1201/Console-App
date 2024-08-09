package consoleapp;

public class Validation {

	public static boolean email_validation(String email) {
		String regx = "[\\s]{0}[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,3}[\\s]{0,3}";
		return email.matches(regx);
	}
	
	public static boolean name_validation(String name) {
		String regx = "[\\s]{0}[a-zA-Z]{1,20}+[\\s]{0,1}[a-zA-Z]{0,50}[\\s]{0,3}";
		return name.matches(regx);
	}
	
	public static boolean phone_validation(String phone) {
		String regx ="[\\s]{0}[6-9][0-9]{9}[\\s]{0,3}";
		return phone.matches(regx);		
	}
	
}
