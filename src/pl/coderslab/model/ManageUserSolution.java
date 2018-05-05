package pl.coderslab.examples.programmingschool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.examples.programmingschool.model.Solution;

public class ManageUserSolution {
	
	public static void view (int id) {
		
		try {
			Solution [] toDo = Solution.loadAllDone(id);
			for(Solution to: toDo)	System.out.println(to.getExercise_id() + ". " + to.getUpdated() + ": " + to.getDescription() + " " );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void add (int id) {
		
		try {
			Solution [] add = Solution.loadAllDone(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
