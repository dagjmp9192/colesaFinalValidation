package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import models.User;

public class UserDao {

	DataSource dataSource;

	//It will be invoked by the Spring framework when
	//the UserDao object is created.
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//Method to return the name of a user
	public String getName(int id) throws Exception
	{
		String name=null;
		//Connection is obtained from the data source.
		Connection con=dataSource.getConnection();
		//Statement object is created
		PreparedStatement stmt=con.prepareStatement(
		"select title,firstName,lastName from UserMaster where id=?");
		stmt.setInt(1, id);	
		ResultSet rset=stmt.executeQuery();
		if(rset.next())
		{
			StringBuilder str=new StringBuilder();
			str.append(rset.getString(1));
			str.append(" ").append(rset.getString(2));
			str.append(" ").append(rset.getString(3));
			name=str.toString();
		}
		con.close();
		return name;
	}
	
	public boolean IsMailIdExists(String mailId) 
	{
		boolean flag=false;
		try{
		Connection con=dataSource.getConnection();
		//Statement object is created
		PreparedStatement stmt=con.prepareStatement(
		"select * from UserMaster where email=?");
		stmt.setString(1, mailId);	
	    ResultSet rset=stmt.executeQuery();
		if(rset.next())
			flag=true;
		
		con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return flag;
		
	}
	public List<User> allUsers()throws Exception{
		ArrayList<User> list=new ArrayList<User>();
		Connection con=dataSource.getConnection();
		PreparedStatement stmt=con.prepareStatement("select * from UserMaster where userType !=?");
		stmt.setInt(1, User.admin);
		ResultSet rset=stmt.executeQuery();
		while(rset.next()){
			User u=new User();
			mapRecord(rset,u);
			list.add(u);
			
		}
		con.close();
		return list;
	}
	public void deleteUser(int id)throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement stmt=con.prepareStatement("delete from UserMaster where id=?");
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
	}
	//Method to save the user
	public void save(User user) throws Exception
	{
		//Connection is obtaiend from the data source.
		Connection con=dataSource.getConnection();
		//Statement object is created
		PreparedStatement stmt=con.prepareStatement(
		"insert into UserMaster (title,firstName,lastName,email,password,userType) values(?,?,?,?,?,?)");
		//parameters are set in the query
		stmt.setString(1, user.getTitle());
		stmt.setString(2, user.getFirstName());
		stmt.setString(3, user.getLastName());
		stmt.setString(4, user.getEmail());
		stmt.setString(5, user.getPassword());
		stmt.setInt(6, user.getUserType());
		//Query is executed
		stmt.executeUpdate();
		//connection is closed.
		con.close();
	}
	//Method to update user profile
		public void updateProfile(User user) throws Exception
		{
			//Connection is obtained from the data source.
			Connection con=dataSource.getConnection();
			//Statement object is created
			PreparedStatement stmt=con.prepareStatement(
			"update UserMaster set title=?,firstName=?, lastName=?, email=?, city=?, expertise=?, court=?, profile=?,mobileNo=? where id=?");
			//parameters are set in the query
			stmt.setString(1, user.getTitle());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getCity());
			stmt.setInt(6, user.getExpertise());
			stmt.setInt(7, user.getCourt());
			stmt.setString(8, user.getProfile());
			stmt.setString(9, user.getMobileNo());
			stmt.setInt(10, user.getId());
			//Query is executed
			stmt.executeUpdate();
			//connection is closed.
			con.close();
		}
	
	//Method to search user using mailId & password
		public boolean find(User user) throws Exception
		{
			System.out.println("finding user using mailId "+user.getEmail());
			boolean flag=false;
			//Connection is obtained from the data source.
			Connection con=dataSource.getConnection();
			//Statement object is created
			PreparedStatement stmt=con.prepareStatement(
			"select * from UserMaster where email=? and password=?");
			//parameters are set in the query
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			//Query is executed
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				flag=true;
				mapRecord(rset,user);
			}
			//connection is closed.
			con.close();
			return flag;
		}
		//Method to return users by type
				public List<User> getUsersByType(int t) throws Exception
				{
					List<User> list=new ArrayList<User>();
					//Connection is obtained from the data source.
					Connection con=dataSource.getConnection();
					//Statement object is created
					PreparedStatement stmt=con.prepareStatement(
					"select * from UserMaster where userType=?");
					//parameters are set in the query
					stmt.setInt(1, t);
					
					//Query is executed
					ResultSet rset=stmt.executeQuery();
					while(rset.next())
					{
						User user=new User();
						mapRecord(rset,user);
						list.add(user);
					}
					//connection is closed.
					con.close();
					return list;
				}
			
		
		//to save image path
		public void updateProfileImage(int userId,String imagePath) throws Exception
		{
			
			Connection con=dataSource.getConnection();
			PreparedStatement stmt=con.prepareStatement(
			"update UserMaster set imageUrl=? where id=?");
			stmt.setString(1, imagePath);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
			con.close();
		}
		//to load a user using its id
		
		public User getById(int id) throws Exception
		{
			User user=new User();
			Connection con=dataSource.getConnection();
			PreparedStatement stmt=con.prepareStatement(
			"select * from UserMaster where id=?");
			stmt.setInt(1, id);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				
				mapRecord(rset,user);
			}
			con.close();
			return user;
		}
	
		
		//utility method to store ResultSet Data in
		//a User object
		private static void mapRecord(ResultSet rset, User user) throws Exception
		{
			user.setId(rset.getInt(1));
			user.setTitle(rset.getString(2));
			user.setFirstName(rset.getString(3));
			user.setLastName(rset.getString(4));
			user.setEmail(rset.getString(5));
			user.setPassword(rset.getString(6));
			user.setCity(rset.getString(7));
			user.setProfile(rset.getString(8));
			user.setImageUrl(rset.getString(9));
			user.setExpertise(rset.getInt(10));
			user.setCourt(rset.getInt(11));
			user.setMobileNo(rset.getString(12));
			user.setUserType(rset.getInt(13));
		}
}
