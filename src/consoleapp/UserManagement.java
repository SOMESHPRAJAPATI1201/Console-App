package consoleapp;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManagement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<UserDTO> userList = new ArrayList<>();
		UserMethods.getInstructions("main");
		while (true) {
			String str = sc.nextLine().trim();
				// Create
			if (str.equalsIgnoreCase("1")) {
				UserMethods.createUser(sc, userList);
				UserMethods.getInstructions("main");
				// Delete
			} else if (str.equalsIgnoreCase("2")) {
				UserMethods.deleteUser(sc, userList);
				UserMethods.getInstructions("main");
				// Update
			} else if (str.equalsIgnoreCase("3")) {
				UserMethods.updateUser(sc, userList, str);
				UserMethods.getInstructions("main");
				// GetAllUser
			} else if (str.equalsIgnoreCase("4")) {
				UserMethods.getAllUsers(userList);
				UserMethods.getInstructions("main");
				// GetSingleUser
			} else if (str.equalsIgnoreCase("5")) {
				UserMethods.getSingleUser(sc, userList);
				UserMethods.getInstructions("main");
				// Exit
			} else if (str.equalsIgnoreCase("6")) {
				System.out.println("Exit Command Selected");
				System.out.println("----------------------");
				break;
				// Wrong Command
			}  else {
				System.out.println("Wrong Command");
				UserMethods.getInstructions("main");
			}
		}
	}
}
