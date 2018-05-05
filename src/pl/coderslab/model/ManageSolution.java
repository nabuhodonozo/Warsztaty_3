package pl.coderslab.examples.programmingschool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import pl.coderslab.examples.programmingschool.model.Exercise;
import pl.coderslab.examples.programmingschool.model.Groups;
import pl.coderslab.examples.programmingschool.model.Solution;
import pl.coderslab.examples.programmingschool.model.User;

public class ManageSolution {

	protected static void addToUser() {
		Scanner scan= new Scanner(System.in);
		int nbExc=0;
		int nbUsr=0;
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		while (true) {
		try {
			Exercise [] exc = Exercise.loadAllExercise();
			for(Exercise exercise: exc) System.out.println(exercise.toString());
		System.out.println();
		System.out.println("Podaj numer zadania: ");
		nbExc=scan.nextInt();
		System.out.println("Podaj numer uzytkownika: ");
		User [] user = User.loadAllUsers();
		for(User ur: user) System.out.println(ur.toString());
		nbUsr=scan.nextInt();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Czy chcesz dodac zadanie nr " + nbExc + " do uzytkownika " + nbUsr  +"?");
		String ans=scan.next();
		Solution solution = new Solution (sqlDate, null, "", nbExc, nbUsr);
		if(ans.equalsIgnoreCase("tak")) {
			try {
				solution.saveToDB();
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

	protected static void addToGroup() {
		Scanner scan = new Scanner (System.in);
		System.out.println("Podaj numer grupy, ktorej chcesz przypisac zadanie");
		int id = scan.nextInt();
		User[] users;
		try {			
			users = User.loadUsersByGroupId(id);
			java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
			int nbExc=0;
			Exercise [] exc = Exercise.loadAllExercise();
			for(Exercise exercise: exc) System.out.println(exercise.toString());
			System.out.println("Podaj numer zadania: ");
			nbExc=scan.nextInt();
			System.out.println("Czy chcesz dodac zadanie nr " + nbExc + " grupy: " + id  +"?");
			String ans=scan.next();
			if(ans.equalsIgnoreCase("tak")) {
			for(User user: users) {
				Solution solution = new Solution (sqlDate, null, "", nbExc, user.getId());
					try {
						solution.saveToDB();
						System.out.println("Dodano zadanie dla grupy");
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	protected static void selectAllForUser() {
		Scanner scan = new Scanner (System.in);
		int id = 0;
		System.out.println("Podaj id uzytkownika, ktorego rozwiazania chcesz wyswietlic: ");
		id=scan.nextInt();
		try {
			Solution[] sol=Solution.loadAllByUserId(id);
			for(Solution sl: sol) System.out.println(sl.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	protected static void selectAllForGroup() {
		Scanner scan = new Scanner (System.in);
		int id = 0;
		System.out.println("Podaj id grupy, ktoj rozwiazania chcesz wyswietlic: ");
		id=scan.nextInt();
		try {
			Solution[] sol=Solution.loadAllByGroupId(id);
			for(Solution sl: sol) System.out.println(sl.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}