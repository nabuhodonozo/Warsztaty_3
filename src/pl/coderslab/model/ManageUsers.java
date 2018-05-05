package pl.coderslab.examples.programmingschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.coderslab.examples.programmingschool.model.Groups;
import pl.coderslab.examples.programmingschool.model.User;

public class ManageUsers {
		
	
	protected static void add() {
			Scanner scan= new Scanner(System.in);
		String username = "";
		String email = "";
		int person_group_id;
		String tryPassword="";
		String password="";
		while (true) {
		System.out.println("Podaj imie i nazwisko uzytkownika:");
		username=scan.nextLine();
		System.out.println("Podaj email uzytkownika:");
		email=scan.nextLine();
		while(true) {	
			System.out.println("Podaj haslo dla uzytkownika");
			tryPassword=scan.nextLine();
			System.out.println("Powtorz haslo");
				if(scan.nextLine().equals(tryPassword)) {
					password=tryPassword;
					break;
					}
				else System.out.println("Bledne haslo");
		}
		System.out.println("Podaj numer grupy dla uzytkownika:");
		person_group_id=scan.nextInt();
		User user = new User(username, email, password, person_group_id);
		System.out.println("Czy chcesz dodac uzytkownika " + username + " email: " + email + " do bazy uzytkownikow?");
		String ans=scan.next();
		if(ans.equalsIgnoreCase("tak")) {
			try {
				user.saveToDB();
				System.out.println("Dodano uzytkownika");
				System.out.println();
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
		System.out.println("Podaj id uzytkownika, ktorego dane chcesz zmienic");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		User user = new User();
		try {
			user=User.loadUserById(id);
			String username = user.getUsername();
			String email = user.getEmail();
			int person_group_id=user.getPerson_group_id();
			String password=user.getPassword();
			String ans;
			int ans2=0;
			System.out.println("Dane: id: " + id + ", username: " + username + ", email: " + email + ", group:");
			System.out.println("Podaj nowa nazwe uzytkownika. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			scan.nextLine();
			ans=scan.nextLine();
			if(!ans.equals("")) {
				username=ans;
				user.setUsername(username);
			}
			System.out.println("Podaj nowy email uzytkownika. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			ans=scan.nextLine();
			if(!ans.equals("")) {
				email=ans;
				user.setEmail(email);
				}
			System.out.println("Podaj nowe haslo uzytkownika. Jesli nie chcesz go zmieniac, pozostaw pole puste:");
				while(true) {
					ans=scan.nextLine();
					if (!ans.equals("")) break;
					else {
					password=ans;
					System.out.println("Powtorz nowe haslo: ");
					if(ans.equals(scan.nextLine())) {
						user.setPassword(password);
						break;
						}
					System.out.println("Bledne haslo. Podaj ponownie nowe haslo:");
					}
				}	
			System.out.println("Podaj nowa grupe uzytkownika. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			ans2=scan.nextInt();
			if(ans2!=0) {
				person_group_id=ans2;						
				user.setPerson_group_id(person_group_id);
				}		
			user.saveToDB();
			System.out.println("Zmieniono dane uzytkownika. Nowe dane: username: " + username + ", email: " + email + ", group:" + person_group_id);
		} catch (SQLException e) {
			System.out.println("Wystapil problem. Sprobuj ponownie");
		}
		
		
	}

	protected static void delete() {
		Scanner scan= new Scanner (System.in);
		int id;
		System.out.println("Podaj id uzytkownika, ktorego chcesz usunac: ");
		id=scan.nextInt();
		scan.nextLine();
		try {
			User user = new User();
			user=User.loadUserById(id);
			System.out.println("Czy na pewno chcesz usunac uzytkownika " + user.getUsername() + ", email: " + user.getEmail());
			if(scan.nextLine().equalsIgnoreCase("tak")) {
				user.delete();
				System.out.println("Usunieto uzytkownika z bazy");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
