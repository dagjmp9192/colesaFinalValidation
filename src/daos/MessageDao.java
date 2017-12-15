package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import models.Message;
import models.*;

public class MessageDao {

	DataSource dataSource;

	//It will be invoked by the Spring framework when
	//the UserDao object is created.
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//Method to save a Message
	public void save(Message msg) throws Exception
	{
		//Connection is obtaiend from the data source.
		Connection con=dataSource.getConnection();
		//Statement object is created
		PreparedStatement stmt=con.prepareStatement(
		"insert into Messages (sender,receiver,title,text,msgDate) values(?,?,?,?,?)");
		//parameters are set in the query
		stmt.setInt(1, msg.getSender());
		stmt.setInt(2, msg.getReceiver());
		stmt.setString(3, msg.getTitle());
		stmt.setString(4, msg.getText());
		stmt.setString(5, msg.getMsgDate());
		//Query is executed
		stmt.executeUpdate();
		//connection is closed.
		con.close();
	}
	//Method to delete a msg
			public void deleteMsg(int id) throws Exception
			{
				//Connection is obtained from the data source.
				Connection con=dataSource.getConnection();
				//Statement object is created
				PreparedStatement stmt=con.prepareStatement(
				"delete from Messages where id=?");
				//parameters are set in the query
				stmt.setInt(1, id);
				//Query is executed
				stmt.executeUpdate();
				//connection is closed.
				con.close();
			}
	//Method to save a reply
		public void saveReply(Reply r) throws Exception
		{
			//Connection is obtained from the data source.
			Connection con=dataSource.getConnection();
			//Statement object is created
			PreparedStatement stmt=con.prepareStatement(
			"insert into Replies (msgId,userId,text,rdate) values(?,?,?,?)");
			//parameters are set in the query
			stmt.setInt(1, r.getMsgId());
			stmt.setInt(2, r.getUserId());
			stmt.setString(3, r.getText());
			stmt.setString(4, r.getDate());
			//Query is executed
			stmt.executeUpdate();
			//connection is closed.
			con.close();
		}
	
	//Method to return all the messages 
	public List<Message> allMessages() throws Exception
	{
		List<Message> list=new ArrayList<Message>();
		//Connection is obtained from the data source.
		Connection con=dataSource.getConnection();
		//Statement object is created
		PreparedStatement stmt=con.prepareStatement(
		"select * from Messages");
			
		//Query is executed
		ResultSet rset=stmt.executeQuery();
		while(rset.next())
		{
			Message m=new Message();
			mapRecord(rset,m);
			list.add(m);
		}
		//connection is closed.
		con.close();
		return list;
	}
	
	//Method to return replies of a messages 
		public List<Reply> repliesOfTheMessage(int msgId) throws Exception
		{
			List<Reply> list=new ArrayList<Reply>();
			//Connection is obtained from the data source.
			Connection con=dataSource.getConnection();
			//Statement object is created
			PreparedStatement stmt=con.prepareStatement(
			"select * from Replies where msgId=?");
			stmt.setInt(1,msgId);	
			//Query is executed
			ResultSet rset=stmt.executeQuery();
			while(rset.next())
			{
				Reply r=new Reply();
				mapRecordToReply(rset,r);
				list.add(r);
			}
			//connection is closed.
			con.close();
			return list;
		}
	
		//Method to return all the messages sent by a user
		public Message getMsgById(int id) throws Exception
		{
			Message msg=null;
			//Connection is obtained from the data source.
			Connection con=dataSource.getConnection();
			//Statement object is created
			PreparedStatement stmt=con.prepareStatement(
			"select * from Messages where id=?");
			//parameters are set in the query
			stmt.setInt(1, id);
			
			//Query is executed
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				msg=new Message();
				mapRecord(rset,msg);
				
			}
			//connection is closed.
			con.close();
			return msg;
		}

		
		//Method to return all the messages sent by a user
				public List<Message> getMsgBySender(int s) throws Exception
				{
					List<Message> list=new ArrayList<Message>();
					//Connection is obtained from the data source.
					Connection con=dataSource.getConnection();
					//Statement object is created
					PreparedStatement stmt=con.prepareStatement(
					"select * from Messages where sender=?");
					//parameters are set in the query
					stmt.setInt(1, s);
					
					//Query is executed
					ResultSet rset=stmt.executeQuery();
					while(rset.next())
					{
						Message m=new Message();
						mapRecord(rset,m);
						list.add(m);
					}
					//connection is closed.
					con.close();
					return list;
				}
				
				//Method to return all the messages received by a user
				public List<Message> getMsgByReceiver(int r) throws Exception
				{
					List<Message> list=new ArrayList<Message>();
					//Connection is obtained from the data source.
					Connection con=dataSource.getConnection();
					//Statement object is created
					PreparedStatement stmt=con.prepareStatement(
					"select * from Messages where receiver=?");
					//parameters are set in the query
					stmt.setInt(1, r);
					
					//Query is executed
					ResultSet rset=stmt.executeQuery();
					while(rset.next())
					{
						Message m=new Message();
						mapRecord(rset,m);
						list.add(m);
					}
					//connection is closed.
					con.close();
					return list;
				}
				
				
		//utility method to store ResultSet Data in
		//a Message object
		private static void mapRecord(ResultSet rset, Message msg) throws Exception
		{
			msg.setId(rset.getInt(1));
			msg.setSender(rset.getInt(2));
			msg.setReceiver(rset.getInt(3));
			msg.setTitle(rset.getString(4));
			msg.setText(rset.getString(5));
			msg.setMsgDate(rset.getString(6));
		}
		//utility method to store ResultSet Data in
				//a Message object
				private static void mapRecordToReply(ResultSet rset, Reply r) throws Exception
				{
					r.setId(rset.getInt(1));
					r.setMsgId(rset.getInt(2));
					r.setUserId(rset.getInt(3));
					r.setText(rset.getString(4));
					r.setDate(rset.getString(5));
				}
}
