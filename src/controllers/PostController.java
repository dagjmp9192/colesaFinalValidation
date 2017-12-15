package controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import daos.PostDao;

import daos.UserDao;
import models.Comment;
import models.Post;

import models.User;

@Controller
public class PostController {
	@Autowired
	HttpSession session;
	@Autowired
	UserDao udao;
	@Autowired
	PostDao pdao;
	


@RequestMapping(value="/newPost.spring")
public ModelAndView post()throws Exception{
	ModelAndView mav=new ModelAndView();
	User user =(User)session.getAttribute("user");
	User pro=udao.getById(user.getId());
	mav.setViewName("newPost");
	mav.addObject("from",pro);
	
	return mav;
}

@RequestMapping(value="/savePost.spring")
public ModelAndView savePost(@ModelAttribute Post post)throws Exception{
	ModelAndView mav =new ModelAndView();
	Date d=new Date();
	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
	post.setDate(df.format(d));
	pdao.savePost(post);
	mav.setViewName("userNotification");
	mav.addObject("message","Your post is successfully submitted.");
	
	return mav;
}

@RequestMapping(value="/viewPosts.spring")
public ModelAndView viewPost()throws Exception{
	ModelAndView mav=new ModelAndView();
	
	List<Post> list=(List<Post>)pdao.allPost();
	
	Iterator<Post> itr=list.iterator();
	while(itr.hasNext())
	{
		Post p=itr.next();
		//setting userName to the post
		User u=udao.getById(p.getUserId());
		p.setUname(u.getTitle()+" "+u.getFirstName()+" "+u.getLastName());
		//setting comments of the post
		p.setComments(pdao.commentsOfThePost(p.getId()));
	}
	
	mav.addObject("posts",list);
	mav.setViewName("posts");	
	
	return mav;
}

@RequestMapping(value="/saveComment.spring")
public ModelAndView saveComment(@RequestParam("id") int id,@RequestParam("comment") String comment)throws Exception{
	User u=(User)session.getAttribute("user");
	Date d=new Date();
	DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
	ModelAndView mav=new ModelAndView();
	pdao.saveComment(id,comment,u.getFirstName(),df.format(d));
	mav.setViewName("userNotification");
	mav.addObject("message", "Your Comment is successfully posted.");
	return mav;
}

/*@RequestMapping(value="/viewPosts.spring")
public ModelAndView viewPost1()throws Exception{
	
	ModelAndView mav=new ModelAndView();
	List<Post> list=(List<Post>)pdao.allPost();
	Iterator<Post> itr=list.iterator();
	while(itr.hasNext())
	{
		Post p=itr.next();
		Date d=new Date();
		DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
		p.setDate(df.format(d));
		User u=udao.getById(p.getUserId());
		p.setUname(u.getTitle()+" "+u.getFirstName()+" "+u.getLastName());
	}
	
	mav.addObject("qlist",list);
	mav.setViewName("visitorPosts");	
	
	return mav;
}*/



}
