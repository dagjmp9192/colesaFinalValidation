package controllers;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import daos.*;
import models.*;


@Controller
public class AdminController {
	@Autowired
	UserDao udao;
	@Autowired
	PostDao pdao;
	@Autowired
	MessageDao mdao;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/allUsers.spring")
	public ModelAndView all()throws Exception{
		User user=(User)session.getAttribute("user");
		ModelAndView mav=new ModelAndView();
		List<User> users=(List<User>)udao.allUsers();
		mav.addObject("users", users);
		mav.setViewName("allUsers");
		return mav;
	}
	
	@RequestMapping(value="/deleteUser.spring")
	public ModelAndView deleteUser(@RequestParam("id") int id)throws Exception{
		ModelAndView mav=new ModelAndView();
		udao.deleteUser(id);
		mav.setViewName("userNotification");
		mav.addObject("message","User Is Successfully Deleted.");
		return mav;
	}
	@RequestMapping(value="/deleteMsg.spring")
	public ModelAndView deleteMsg(@RequestParam("id") int id)throws Exception{
		ModelAndView mav=new ModelAndView();
		mdao.deleteMsg(id);
		mav.setViewName("userNotification");
		mav.addObject("message","Question is successfully deleted.");
		return mav;
	}
	@RequestMapping(value="/allPosts.spring")
	public ModelAndView fetchPost()throws Exception{
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
		mav.setViewName("allPosts");	
		
		return mav;
	}
	
	@RequestMapping(value="/allMsg.spring")
	public ModelAndView fetchMessages()throws Exception{
		ModelAndView mav=new ModelAndView();
		
		List<Message> list=(List<Message>)mdao.allMessages();
		
		Iterator<Message> itr=list.iterator();
		while(itr.hasNext())
		{
			Message m=itr.next();
			//setting userName to the post
			User r=udao.getById(m.getReceiver());
			m.setReceiverName(r.getTitle()+" "+r.getFirstName()+" "+r.getLastName());
			User s=udao.getById(m.getSender());
			m.setSenderName(s.getTitle()+" "+s.getFirstName()+" "+s.getLastName());
			//setting comments of the post
			m.setReplies(mdao.repliesOfTheMessage(m.getId()));
		}
		mav.addObject("messages",list);
		mav.setViewName("allMsg");	
		
		return mav;
	}
	
	@RequestMapping(value="/deletePost.spring")
	public ModelAndView deletePost(@RequestParam("id") int id)throws Exception{
		ModelAndView mav=new ModelAndView();
		pdao.deletePost(id);
		mav.setViewName("userNotification");
		mav.addObject("message","Post Is Successfully Deleted.");
		return mav;
	}
	
	@RequestMapping(value="/deleteComment.spring")
	public ModelAndView delete2(@RequestParam("id") int id)throws Exception{
		ModelAndView mav=new ModelAndView();
		pdao.deleteComment(id);
		mav.setViewName("userNotification");
		mav.addObject("message","Comment Is Successfully Deleted.");
		return mav;
	}



}
