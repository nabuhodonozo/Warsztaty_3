package pl.coderslab.examples.programmingschool;


import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.examples.programmingschool.model.Exercise;

public class ManageExercise {
	protected static void add() {
		Scanner scan= new Scanner(System.in);
		String title = "";
		String description="";
		while (true) {
		System.out.println("Podaj tytul zadania: ");
		title=scan.nextLine();
		System.out.println("Podaj opis zadania: ");
		description=scan.nextLine();
		Exercise exercise = new Exercise(title, description);
		System.out.println("Czy chcesz dodac zadanie: " + title);
		String ans=scan.next();
		if(ans.equalsIgnoreCase("tak")) {
			try {
				exercise.saveToDB();
				System.out.println("Dodano zadanie");
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
		System.out.println("Podaj numer zadanie ktore chcesz edytowac");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Exercise exercise = new Exercise();
		try {
			exercise=Exercise.loadExerciseById(id);
			String title = exercise.getTitle();
			String description = exercise.getDescription();
			String ans;
			System.out.println("Dane: id: " + id + ", tytul: " + title + ", opis: " + description);
			System.out.println("Podaj nowy tytul zadania. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			scan.nextLine();
			ans=scan.nextLine();
			if(!ans.equals("")) {
				title=ans;
				exercise.setTitle(title);
			}
			System.out.println("Podaj opis zadania. Jesli nie chcesz nic zmieniac, pozostaw pole puste:");
			ans=scan.nextLine();
			if(!ans.equals("")) {
				description=ans;
				exercise.setDescription(description);
			}
			exercise.saveToDB();
			System.out.println("Zmieniono zadanie");
		} catch (SQLException e) {
			System.out.println("Wystapil problem. Sprobuj ponownie");
		}
		
	}

	protected static void delete() {
		System.out.println("Podaj numer zadania, ktore chcesz usunac");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Exercise exercise = new Exercise();
		try {
			exercise=Exercise.loadExerciseById(id);
			String title = exercise.getTitle();
			String ans;
			System.out.println("Dane: id: " + id + ", tytul: " + title);
			System.out.println("Czy na pewno chcesz usunac zadanie?");
			scan.nextLine();
			ans=scan.nextLine();
			if(ans.equalsIgnoreCase("tak")) { 
				exercise.delete();
				System.out.println("Usunieto zadanie");
			}
		} catch (SQLException e) {
			System.out.println("Wystapil problem. Sprobuj ponownie");
		}
	}
}
