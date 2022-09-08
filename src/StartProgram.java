import java.util.List;
import java.util.Scanner;

import controller.GymMemberHelper;
import model.GymMember;

/**
 * @author Tony Ehlert - aehlert
 * CIS175 - Fall 2022
 * Sep 7, 2022
 */
public class StartProgram {

	static Scanner in= new Scanner(System.in);
	static GymMemberHelper gmh = new GymMemberHelper();
	
	private static void addMember() {
		System.out.print("Enter the member's first name: ");
		String firstName = in.nextLine();
		System.out.print("Enter the member's last name: ");
		String lastName = in.nextLine();
		System.out.print("Enter the member's age: ");
		int age = in.nextInt();
		GymMember toAdd = new GymMember(firstName, lastName, age);
		gmh.insertMember(toAdd);

	}

	private static void deleteMember() {
		
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by First Name");
		System.out.println("2 : Search by Last Name");
		System.out.print("Enter your selection: ");
		int searchBy = in.nextInt();
		in.nextLine();
		List<GymMember> foundMembers;
		
		if (searchBy == 1) {
			System.out.print("Enter the member's First Name: ");
			String firstName = in.nextLine();
			foundMembers = gmh.searchForMemberByFirstName(firstName);
		} else {
			System.out.print("Enter the member's Last Name: ");
			String lastName = in.nextLine();
			foundMembers = gmh.searchForMemberByLastName(lastName);
		}
		
		if (!foundMembers.isEmpty()) {
			System.out.println("Found Results.");
			for (GymMember g : foundMembers) {
				System.out.println(g.getId() + " : " + g.returnMemberInfo());
			}
			System.out.print("Enter the member's first name to delete: ");
			String firstName = in.nextLine();
			System.out.print("Enter the member's last name to delete: ");
			String lastName = in.nextLine();
			GymMember toDelete = new GymMember(firstName, lastName);
			gmh.deleteMember(toDelete);
		
		} else {
			System.out.println("---- No results found");
		}
		
		

	}

	private static void editMember() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by First Name");
		System.out.println("2 : Search by Last Name");
		System.out.print("Enter your selection: ");
		int searchBy = in.nextInt();
		in.nextLine();
		List<GymMember> foundMembers;
		
		if (searchBy == 1) {
			System.out.print("Enter the member's First Name: ");
			String firstName = in.nextLine();
			foundMembers = gmh.searchForMemberByFirstName(firstName);
		} else {
			System.out.print("Enter the member's Last Name: ");
			String lastName = in.nextLine();
			foundMembers = gmh.searchForMemberByLastName(lastName);
		}
		
		if (!foundMembers.isEmpty()) {
			System.out.println("Found Results.");
			for (GymMember g : foundMembers) {
				System.out.println(g.getId() + " : " + g.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			GymMember toEdit = gmh.searchForMemberById(idToEdit);
			System.out.println("Retrieved " + toEdit.getFirstName() + " from " + toEdit.getLastName());
			System.out.println("1 : Update First Name");
			System.out.println("2 : Update Last Name");
			System.out.println("3 : Update Age");
			System.out.println("4 : Update Waiver Status");
			System.out.print("Enter your selection: ");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New First Name: ");
				String newFirstName = in.nextLine();
				toEdit.setFirstName(newFirstName);
			} else if (update == 2) {
				System.out.print("New Last Name: ");
				String newLastName = in.nextLine();
				toEdit.setLastName(newLastName);
			} else if (update == 3) {
				System.out.print("New Age: ");
				int newAge = in.nextInt();
				toEdit.setAge(newAge);
			}else if (update == 4) {
				System.out.print("Waiver Signed? true or false: ");
				boolean newWaiverStatus = Boolean.parseBoolean(in.nextLine());
				toEdit.setWaiverSigned(newWaiverStatus);
			}

			gmh.updateMember(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	public static void main(String[] args) {
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Welcome to CrossFit Ankeny's Member Portal!");
		while (goAgain) {
			System.out.println("Select an option:");
			System.out.println("1 -- Add a member");
			System.out.println("2 -- Edit a member");
			System.out.println("3 -- Delete a member");
			System.out.println("4 -- View all members");
			System.out.println("5 -- Exit the kiosk");
			System.out.print("Enter your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addMember();
			} else if (selection == 2) {
				editMember();
			} else if (selection == 3) {
				deleteMember();
			} else if (selection == 4) {
				viewMembers();
			} else {
				gmh.cleanUp();
				System.out.println("Thanks for Visiting!");
				goAgain = false;
			}

		}

	}

	private static void viewMembers() {
		// TODO Auto-generated method stub
		List<GymMember> allMembers = gmh.showAllMembers();
		for(GymMember singleMember : allMembers) {
			System.out.println(singleMember.returnMemberInfo());
		}
	}

}
