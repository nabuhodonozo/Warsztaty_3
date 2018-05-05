package pl.coderslab.examples.programmingschool;

import java.util.Scanner;

public class UserPage {

	public static void main(String[] args) {
//		int id = Integer.parseInt(args[0]);
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj id: ");
		int id = scan.nextInt();
		System.out.println("1. Dodawanie rozwiazan");
		System.out.println("2. Przegladanie rozwiazan");
		switch(scan.nextInt()) {
		case 1:	{
				
			}
		case 2: {
			ManageUserSolution.view(id);
		}
		}
	}

}
