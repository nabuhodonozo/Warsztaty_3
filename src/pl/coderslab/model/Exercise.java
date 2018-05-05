package pl.coderslab.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.DbUtil;


public class Exercise {
	private int id;
	private String title;
	private String description;
	
	
	public Exercise (String title, String description) {
		this.title = title;
		this.description=description;
	}
	
	public Exercise() {}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void saveToDB() throws SQLException {
		if	(this.id == 0) {
			
			String sql = "INSERT INTO Exercise(title, description) VALUES (?,?);";
			String generatedColumns[] = {"ID"};
			PreparedStatement preparedStatement;
			preparedStatement = DbUtil.connect().prepareStatement(sql, generatedColumns);
			preparedStatement.setString(1, this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if	(rs.next())	{
				this.id	= rs.getInt(1);
			}
			preparedStatement.close();
			}
		else {
			String	sql	= "UPDATE Exercise SET title=?, description=? where id = ?";
			PreparedStatement	preparedStatement;
			preparedStatement	=	DbUtil.connect().prepareStatement(sql);
			preparedStatement.setString(1,	this.title);
			preparedStatement.setString(2, this.description);
			preparedStatement.setInt(3,	this.id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			}
	}

		static public Exercise loadExerciseById(int id) throws SQLException {
			String sql = "SELECT * FROM	Exercise where id=?";
			PreparedStatement preparedStatement;
			preparedStatement = DbUtil.connect().prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Exercise loadedExercise = new Exercise();
					loadedExercise.id = resultSet.getInt("id");
					loadedExercise.title	= resultSet.getString("title");
					loadedExercise.description	= resultSet.getString("description");
					return	loadedExercise;
			}
			return	null;
		}
		
		static	public Exercise[] loadAllExercise() throws SQLException	{
			ArrayList<Exercise> exercise = new ArrayList<Exercise>();
			String	sql	= "SELECT * FROM Exercise";
			PreparedStatement preparedStatement;
			preparedStatement = DbUtil.connect().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
					Exercise loadedExercise = new Exercise();
					loadedExercise.id = resultSet.getInt("id");
					loadedExercise.title = resultSet.getString("title");
					loadedExercise.description = resultSet.getString("description");
					exercise.add(loadedExercise);
			}
			Exercise[]	uArray	=	new	Exercise[exercise.size()];
			uArray	=	exercise.toArray(uArray);
			return uArray;
		}
				

		public void delete() throws SQLException	{
			if (this.id != 0) {
				String	sql	= "DELETE FROM Exercise WHERE id= ?";
				PreparedStatement preparedStatement;
				preparedStatement = DbUtil.connect().prepareStatement(sql);
				preparedStatement.setInt(1, this.id);
				preparedStatement.executeUpdate();
				this.id=0;
			}
			
		}
		@Override
		public String toString() {
		String exerciseToString = "id:" + this.id +  " tytul: " + this.title + " ||  opis: " + this.description;
		return exerciseToString;
		}
		
}