package pl.coderslab.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.DbUtil;



public class User {
	
	private	int	id;
	private	String	username;
	private	String	password;
	private	String	email;
	private int person_group_id;
	
	public	User(String username, String email, String password, int person_group_id) {
		this.username = username;
		this.email = email;
		this.person_group_id=person_group_id;
		this.setPassword(password);	
		
	}
	public	User()	{}
	public String getUsername() {
		return username;}
	public void setUsername(String username) {
		this.username = username;}
	public String getEmail() {
		return email;}
	public void setEmail(String email) {
		this.email = email;}
	public int getPerson_group_id() {
		return person_group_id;}
	public void setPerson_group_id(int person_group_id) {
		this.person_group_id = person_group_id;	}
	public int getId() {
		return id;}
	public String getPassword() {
		return password;}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public void saveToDB() throws SQLException	{
		if	(this.id == 0) {
				String sql = "INSERT INTO Users(username, email, password, person_group_id) VALUES (?, ?, ?, ?);";
				String generatedColumns[] = {"ID"};
				PreparedStatement preparedStatement;
				preparedStatement = DbUtil.connect().prepareStatement(sql, generatedColumns);
				preparedStatement.setString(1, this.username);
				preparedStatement.setString(2, this.email);
				preparedStatement.setString(3, this.password);
				preparedStatement.setInt(4, this.person_group_id);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if	(rs.next())	{
					this.id	= rs.getInt(1);
					}
				preparedStatement.close();
				}
		else	{
				String	sql	=	"UPDATE	Users	SET	username=?,	email=?,	password=?, person_group_id=?	where	id	=	?";
				PreparedStatement	preparedStatement;
				preparedStatement	=	DbUtil.connect().prepareStatement(sql);
				preparedStatement.setString(1,	this.username);
				preparedStatement.setString(2,	this.email);
				preparedStatement.setString(3,	this.password);
				preparedStatement.setInt(4, this.person_group_id);
				preparedStatement.setInt(5,	this.id);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
	}

	static public User loadUserById(int id) throws SQLException {
		String sql = "SELECT * FROM	Users WHERE id=?";
		PreparedStatement preparedStatement;
		preparedStatement = DbUtil.connect().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
				User loadedUser = new User();
				loadedUser.id = resultSet.getInt("id");
				loadedUser.username	= resultSet.getString("username");
				loadedUser.password	= resultSet.getString("password");
				loadedUser.email = resultSet.getString("email");
				loadedUser.person_group_id = resultSet.getInt("person_group_id");
				return	loadedUser;
		}
		return	null;
	}
	
	static public User [] loadUsersByGroupId(int id) throws SQLException {
		ArrayList<User>	users	=	new	ArrayList<User>();
		String	sql	=	"SELECT	*	FROM Users WHERE person_group_id=?";
		PreparedStatement preparedStatement;
		preparedStatement = DbUtil.connect().prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet	resultSet	=	preparedStatement.executeQuery();
		while	(resultSet.next())	{
				User	loadedUser	=	new	User();
				loadedUser.id	=	resultSet.getInt("id");
				loadedUser.username	=	resultSet.getString("username");
				loadedUser.password	=	resultSet.getString("password");
				loadedUser.email	=	resultSet.getString("email");
				loadedUser.person_group_id = resultSet.getInt("person_group_id");
				users.add(loadedUser);
		}
		User[]	uArray	=	new	User[users.size()];
		uArray	=	users.toArray(uArray);
		return uArray;
	}
	
	static	public	User[]	loadAllUsers() throws SQLException	{
		ArrayList<User>	users	=	new	ArrayList<User>();
		String	sql	=	"SELECT	*	FROM	Users";
		PreparedStatement	preparedStatement;
		preparedStatement	=	DbUtil.connect().prepareStatement(sql);
		ResultSet	resultSet	=	preparedStatement.executeQuery();
		while	(resultSet.next())	{
				User	loadedUser	=	new	User();
				loadedUser.id	=	resultSet.getInt("id");
				loadedUser.username	=	resultSet.getString("username");
				loadedUser.password	=	resultSet.getString("password");
				loadedUser.email	=	resultSet.getString("email");
				loadedUser.person_group_id = resultSet.getInt("person_group_id");
				users.add(loadedUser);
		}
		User[]	uArray	=	new	User[users.size()];
		uArray	=	users.toArray(uArray);
		return uArray;
	}

	public void delete() throws SQLException	{
		if (this.id != 0) {
			String	sql	= "DELETE FROM Users WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = DbUtil.connect().prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id=0;
		}
	}
	
	@Override
	public String toString() {
	String userToString =this.id + ": " + this.username + "|| email: " + this.email + "|| group: " + this.person_group_id;
	return userToString;
	}
}