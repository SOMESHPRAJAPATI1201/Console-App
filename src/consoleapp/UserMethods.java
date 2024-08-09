package consoleapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserMethods {

	// delete
	public static void deleteUser(Scanner sc, List<UserDTO> userList) {
		System.out.println("User Deletion Command Selected");
		System.out.println("----------------------");
		try {
			System.out.println("Enter User's Email : ");
			String email = sc.nextLine().trim();
			if (Validation.email_validation(email)) {
				boolean userFound = false;
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getEmail().equalsIgnoreCase(email)) {
						System.out.println("User " + userList.get(i).getName() + ", Deleted Successfully.");
						userList.remove(i);
						userFound = true;
						break;
					}
				}
				if (!userFound) {
					System.err.println("Email Doesn't Exist Or Invalid.");
				}
			} else {
				System.err.println("Invalid Email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update
	public static void updateUser(Scanner sc, List<UserDTO> userList, String str) {
		System.out.println("User Updation Command Selected");
		System.out.println("----------------------");
		try {
			boolean flag = false;
			System.out.println("Enter User's Email : ");
			String email = sc.nextLine();
			System.out.println("------------------------------------------------");
			if (Validation.email_validation(email)) {
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getEmail().equalsIgnoreCase(email.trim())) {
						flag = true;
						getInstructions("update");
						str = sc.nextLine().trim();
						switch (str) {

						// update name
						case "1":
							updateName(sc, userList, i);
							break;

						// update email
						case "2":
							updateEmail(sc, userList, i);
							break;

						// update phone
						case "3":
							updatePhone(sc, userList, i);
							break;

						// update all details
						case "4":
							// name
							boolean a = updateName(sc, userList, i);
							if (a) {
								// email
								a = updateEmail(sc, userList, i);
								if (a) {
									// phone
									updatePhone(sc, userList, i);
								}
							}
							break;
						default: {
							System.err.println("Invalid Input");
						}
						}
					}
				}
			}
			if (!flag) {
				System.err.println("Email Doesn't Exist Or Invalid.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// create
	public static void createUser(Scanner sc, List<UserDTO> userList) {
		System.out.println("User Register Command");
		System.out.println("----------------------");
		try {
			boolean flag = true;
			System.out.println("Enter User's Full Name : ");
			String fullName = sc.nextLine();
			if (Validation.name_validation(fullName)) {
				System.out.println("Enter User's Email : ");
				String email = sc.nextLine();
				if (Validation.email_validation(email)) {
					for (int i = 0; i < userList.size(); i++) {
						if (userList.get(i).getEmail().equalsIgnoreCase(email)) {
							flag = false;
						}
					}
					System.out.println("Enter count of phone numbers you want to save : ");
					ArrayList<String> phoneNo = new ArrayList<>();
					if (flag) {
						flag = false;
						try {
							int phoneCount = Integer.parseInt(sc.nextLine());
							for (int i = 0; i < phoneCount; i++) {
								System.out.println("Enter " + (i + 1) + " Number : ");
								String phone = sc.nextLine();
								if (Validation.phone_validation(phone)) {
									phoneNo.add(phone.trim());
									if (phoneNo.size() - 1 == i) {
										flag = true;
									}
								} else {
									System.err.println("Invalid Phone");
									break;
								}
							}
						} catch (Exception e) {
							System.err.println("Invalid Input");
						}
						//
						if (flag) {
							userList.add(
									new UserDTO(fullName.toLowerCase().trim(), email.toLowerCase().trim(), phoneNo));
							System.out.println(fullName.toUpperCase() + " , User Created Successfully");
						}
					} else {
						System.err.println("User Associated With " + email + " Already Exists.");
					}
				} else {
					System.err.println("Invalid Email");
				}

			} else {
				System.err.println("Name Is Not Entered Correctly.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// fetchAllUser
	public static void getAllUsers(List<UserDTO> userList) {
		System.out.println("Fetch All User Command Selected");
		System.out.println("----------------------");
		try {
			if (userList.size() == 0) {
				System.out.println("No Records Found");
			} else {
				System.out.println("------------------------------------------------");
				System.out.println(userList.size() + " Users Data Fetched.");
				System.out.println("------------------------------------------------");
				printAllUserData(userList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// fetchSingleUser
	public static void getSingleUser(Scanner sc, List<UserDTO> userList) {
		System.out.println("Fetch Single User Command Selected");
		try {
			System.out.println("Enter Users's Email : ");
			String email = sc.nextLine().trim();
			if (Validation.email_validation(email)) {
				System.out.println("------------------------------------------------");
				UserDTO userdto = null;
				try {
					userdto = checkEmail(userList, email);
					if (userdto != null) {
						printUserDetails(userdto);
					}
				} catch (Exception e) {
					throw new RuntimeException("UserNotFoundException");
				}
			} else {
				System.err.println("Email Doesn't Exist Or Invalid.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// recallableMethods
	public static void getInstructions(String ins) {
		if (ins.equalsIgnoreCase("main")) {
			System.out.println("Enter Command : ");
			System.out.println("Enter 1 for user registration : ");
			System.out.println("Enter 2 for user deletion : ");
			System.out.println("Enter 3 for update user : ");
			System.out.println("Enter 4 for fetch all user : ");
			System.out.println("Enter 5 for fetch single user for  : ");
			System.out.println("Enter 6 for exit : ");
		} else if (ins.equalsIgnoreCase("update")) {
			System.out.println("Enter 1 For Update Name : ");
			System.out.println("Enter 2 For Email : ");
			System.out.println("ENTER 3 For Update Phone : ");
			System.out.println("ENTER 4 For Update All User Details : ");
		}
	}

	public static void printUserDetails(UserDTO userDto) {
		System.out.println(userDto.getName() + " :: " + userDto.getEmail() + " :: " + userDto.getPhone());
		System.out.println("------------------------------------------------");
	}

	public static UserDTO checkEmail(List<UserDTO> userDto, String email) {
		return userDto.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).collect(Collectors.toList()).get(0);
	}

	public static void printAllUserData(List<UserDTO> userList) {
		userList.forEach(t -> printUserDetails(t));
		System.out.println("------------------------------------------------");

	}

	public static boolean updateName(Scanner sc, List<UserDTO> userList, int i) {
		System.out.println("Enter New Name : ");
		String fullName = sc.nextLine();
		if (Validation.name_validation(fullName)) {
			userList.get(i).setName(fullName.toLowerCase().trim());
			System.out.println("Name Updated Successfully");
			return true;
		} else {
			System.err.println("Name Not Entered Correctly.");
			return false;
		}
	}

	public static boolean updateEmail(Scanner sc, List<UserDTO> userList, int i) {
		System.out.println("Enter New Email : ");
		String email = sc.nextLine();
		if (Validation.email_validation(email)) {
			userList.get(i).setEmail(email.toLowerCase().trim());
			System.out.println("New Email Updated Successfully");
			return true;
		} else {
			System.err.println("Invalid Email ");
			return false;
		}
	}

	public static boolean updatePhone(Scanner sc, List<UserDTO> userList, int i) {
		System.out.println("Enter Existing Phone No : ");
		String oldPhone = sc.nextLine();
		if (Validation.phone_validation(oldPhone)) {
			System.out.println("Enter New Phone No : ");
			String newPhone = sc.nextLine();
			if (Validation.phone_validation(newPhone)) {
				userList.get(i).setPhone(oldPhone.trim(), newPhone.trim());
				System.out.println("Phone Updated Successfully");
				return true;
			} else {
				System.err.println("Invalid Phone");
				return false;
			}
		} else {
			System.err.println("Invalid Phone");
			return false;
		}
	}

}
