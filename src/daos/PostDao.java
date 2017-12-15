package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import models.Comment;
import models.Post;


public class PostDao {

	//Dependency of this dao is on DataSource
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
public void savePost(Post p)throws Exception{
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("insert into Posts (userId,title,contents,pdate) values(?,?,?,?)");
	stmt.setInt(1, p.getUserId());
	stmt.setString(2, p.getTitle());
	stmt.setString(3, p.getContents());
	stmt.setString(4, p.getDate());
	stmt.executeUpdate();
	con.close();
}

public List<Post> allPost()throws Exception{
	ArrayList<Post> qlist=new ArrayList<Post>();
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("select * from Posts");
	ResultSet rset=stmt.executeQuery();
	while(rset.next())
	{
		Post p=mapRecord(rset);
		qlist.add(p);
	}
	con.close();
	return qlist;
}
//utility method to map a record to an Post object
public Post mapRecord(ResultSet rset) throws Exception
{
Post p=new Post();
p.setId(rset.getInt(1));
p.setUserId(rset.getInt(2));
p.setTitle(rset.getString(3));
p.setContents(rset.getString(4));
p.setDate(rset.getString(5));
return p;
}

public void saveComment(int id,String comment,String uname,String date)throws Exception{
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("insert into Comments(postId,contents,uname,cdate) values(?,?,?,?)");
	stmt.setInt(1, id);
	stmt.setString(2, comment);
	stmt.setString(3, uname);
	stmt.setString(4, date);
	stmt.executeUpdate();
	con.close();
}

public List<Comment> commentsOfThePost(int postId)throws Exception{
	ArrayList<Comment> qlist=new ArrayList<Comment>(); 
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("select * from Comments where postId=?");
	stmt.setInt(1,postId);
	ResultSet rset=stmt.executeQuery();
	while(rset.next())
	{
		qlist.add(mapRecordToComment(rset));
	}
	con.close();
	return qlist;
}

public Comment mapRecordToComment(ResultSet rset) throws Exception
{
Comment c=new Comment();
c.setId(rset.getInt(1));
c.setPostId(rset.getInt(2));
c.setContents(rset.getString(3));
c.setUname(rset.getString(4));
c.setDate(rset.getString(5));
return c;
}


public void deletePost(int id)throws Exception{
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("delete from Posts where id=?");
	stmt.setInt(1, id);
	stmt.executeUpdate();
	con.close();
}

public void deleteComment(int id)throws Exception{
	Connection con=dataSource.getConnection();
	PreparedStatement stmt=con.prepareStatement("delete from Comments where id=?");
	stmt.setInt(1, id);
	stmt.executeUpdate();
	con.close();
}


}
