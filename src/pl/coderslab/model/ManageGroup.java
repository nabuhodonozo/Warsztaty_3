package pl.coderslab.examples.programmingschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.examples.programmingschool.model.Groups;

public class ManageGroup {
	protected static void add() {
		Scanner scan= new Scanner(System.in);
		String name = "";
		while (true) {
		System.out.println("Podaj nazwe grupy: ");
		name=scan.nextLine();
		Groups group = new Groups(name);
		System.out.println("Czy chcesz dodac grupe: " + name);
		String ans=scan.next();
		if(ans.equalsIgnoreCase("tak")) {
			try {
				group.saveToDB();
				break;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		else
			System.out.println("Podaj dane ponownie");
			scan.nextLine();
		}
		}
	

	protected static void edit() {
		System.out.println("Podaj numer grupy, ktora chcesz edytowac");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Groups group = new Groups();
		try {
			group=Groups.loadGroupById(id);
			String name = group.getName();
			String ans;
			System.out.println("Dane: id: " + id + ", name: " + name);
			System.out.println("Podaj nowa nazwe grupy. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			scan.nextLine();
			ans=scan.nextLine();
			if(!ans.equals("")) {
				name=ans;
				group.setName(name);
			}
			group.saveToDB();
			System.out.println("Zmieniono dane grupy. Nowa nazwa: " + name);
		} catch (SQLException e) {
			System.out.println("Wystapil problem. Sprobuj ponownie");
		}
		
	}

	protected static void delete() {
		System.out.println("Podaj numer grupy, ktora chcesz usunac");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Groups group = new Groups();
		try {
			group=Groups.loadGroupById(id);
			String name = group.getName();
			String ans;
			System.out.println("Dane: id: " + id + ", name: " + name);
			System.out.println("Czy na pewno chcesz usunac grupe?");
			scan.nextLine();
			ans=scan.nextLine();
			if(ans.equalsIgnoreCase("tak")) { 
				group.delete();
				System.out.println("Usunieto grupe");
			}
		} catch (SQLException e) {
			System.out.println("Wystapil problem. Sprobuj ponownie");
		}
	}

}
