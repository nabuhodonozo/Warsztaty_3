package pl.coderslab.model;


import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.model.model.Exercise;
import pl.coderslab.model.model.Groups;
import pl.coderslab.model.model.User;


public class Homepage {

public static void main(String[] args) {
	Scanner scan = new Scanner (System.in);
	while(true) {
		System.out.println("Witaj w programie do zarządzania bazą danych szkoły programowania. Wybierz, co chcesz teraz zrobić:");
		System.out.println("1. Zarzadzaj uzytkownikami");
		System.out.println("2. Zarzadzaj zadaniami");
		System.out.println("3. Zarzadzaj grupami użytkownikow");
		System.out.println("4. Przypisz zadania");
		System.out.println("5. Wyjdz z programu");
		switch(scan.nextInt()) {
		case 1:  {
				System.out.println();
				System.out.println("Lista wszystkich uzytkownikow: ");
				User [] usr;
				try {
					usr = User.loadAllUsers();
					for(User us: usr) System.out.println(us.toString());
				} catch (SQLException e) {}
				System.out.println();
				System.out.println("Zarzadzanie uzytkownikami");
				System.out.println("1. Dodaj uzytkownika");
				System.out.println("2. Edytuj uzytkownika");
				System.out.println("3. Usun uzytkownika");
				System.out.println("4. Powrot do menu");
					switch(scan.nextInt()) {
					case 1: {
						ManageUsers.add();
						break;
					}
					case 2: {
						ManageUsers.edit();
						break;
					}
					case 3: {
						ManageUsers.delete();
						break;
					}
					case 4: {
						break;
					}
					}
					break;
			}
		case 2:  {
					System.out.println();
					System.out.println("Lista wszystkich zadan: ");
					Exercise[] exc;
					try {
						exc = Exercise.loadAllExercise();
						for(Exercise exercise: exc) System.out.println(exercise.toString());
					} catch (SQLException e) {}
					System.out.println();
					System.out.println("Zarzadzanie zadaniami");
					System.out.println("1. Dodaj zadanie");
					System.out.println("2. Edytuj zadanie");
					System.out.println("3. Usun zadanie");
					System.out.println("4. Powrot do menu");
					switch(scan.nextInt()) {
					case 1: {
						ManageExercise.add();
						break;
					}
					case 2: {
						ManageExercise.edit();
						break;
					}
					case 3: {
						ManageExercise.delete();
						break;
					}
					case 4: {
						break;
					}
					}
					break;
			}
		case 3:  {
					System.out.println();
					System.out.println("Lista wszystkich grup: ");
					Groups [] group;
					try {
						group = Groups.loadAllGroups();
						for(Groups gr: group) System.out.println(gr.toString());
					} catch (SQLException e) {}
					System.out.println();
					System.out.println("Zarzadzanie grupami uzytkownikow");
					System.out.println("1. Dodaj grupe");
					System.out.println("2. Edytuj grupe");
					System.out.println("3. Usun grupe");
					System.out.println("4. Powrot do menu");
					switch(scan.nextInt()) {
					case 1: {
						ManageGroup.add();
						break;
					}
					case 2: {
						ManageGroup.edit();
						break;
					}
					case 3: {
						ManageGroup.delete();
						break;
					}
					case 4: {
						break;
					}
					}
					break;
			}
		case 4: {
					System.out.println("Przypisywanie zadan");
					System.out.println("1. Dodaj zadanie do uzytkownika");
					System.out.println("2. Dodaj zadanie do grupy uzytkownikow");
					System.out.println("3. Przeglad rozwiazan");
					System.out.println("4. Powrot do menu");
					switch(scan.nextInt()) {
					case 1: {
						ManageSolution.addToUser();
						break;
					}
					case 2: {
						ManageSolution.addToGroup();
						break;
					}
					case 3: {
						System.out.println();
						System.out.println("Przeglad rozwiazan");
						System.out.println("1. Przeglad rozwiazan wybranego uzytkownika");
						System.out.println("2. Przeglad rozwiazan grupy uzytkownikow");
							switch(scan.nextInt()) {
							case 1: {
								ManageSolution.selectAllForUser();
								break;
							}
							case 2: {
								ManageSolution.selectAllForGroup();
								break;
							}
							}
					}
					case 4: {
						break;
					}
					}
					break;
				}
		case 5: {	
				return;
			}
		
		}
	}
}
}
