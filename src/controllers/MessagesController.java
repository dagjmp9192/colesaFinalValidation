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

import daos.MessageDao;
import daos.UserDao;
import models.*;

@Controller
public class MessagesController {

	@Autowired
	MessageDao mdao;
	@Autowired
	UserDao udao;
	@Autowired
	HttpSession session;
	//method to generate send Message form
	@RequestMapping("/msgForm.spring")
	public ModelAndView msgForm(
			@RequestParam int receiver)
	{
		ModelAndView mav=new ModelAndView();
		mav.addObject("receiver",receiver);
		try
		{
		User lawyer=udao.getById(receiver);
		String rname=lawyer.getTitle()+" "+lawyer.getFirstName()+" "+lawyer.getLastName();
		mav.addObject("receiverName",rname);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		mav.setViewName("msgForm");
		return mav;
	}
	//method to display received Messages 
		@RequestMapping("/viewReceivedMsg.spring")
		public ModelAndView getReceivedMessages()
		{
			ModelAndView mav=new ModelAndView();
			try{
			//User is obtained from the session
			User user=(User)session.getAttribute("user");
			List<Message> msgList=null;
				msgList=mdao.getMsgByReceiver(user.getId());
		
			Iterator<Message>	itr=msgList.iterator();
			while(itr.hasNext())
			{
				//Fetching sender name of each message
				Message msg=itr.next();
				msg.setSenderName(udao.getName(msg.getSender()));
			   //Fetching replies of the message
				List<Reply> rlist=mdao.repliesOfTheMessage(msg.getId());
			    msg.setReplies(rlist);
			}
				
			mav.addObject("messages",msgList);
			mav.setViewName("displayReceivedMsg");
			}catch(Exception e)
			{
				System.out.println(e);
				mav.setViewName("error");
			}
			return mav;
		}
	
		//method to display sent Messages 
				@RequestMapping("/viewSentMsg.spring")
				public ModelAndView getSentMessages()
				{
					ModelAndView mav=new ModelAndView();
					try{
					//User is obtained from the session
					User user=(User)session.getAttribute("user");
					List<Message> msgList=null;
						msgList=mdao.getMsgBySender(user.getId());
				
					Iterator<Message>	itr=msgList.iterator();
					while(itr.hasNext())
					{
						//Fetching Receiver name of each message
						Message msg=itr.next();
						msg.setReceiverName(udao.getName(msg.getReceiver()));
						 //Fetching replies of the message
						List<Reply> rlist=mdao.repliesOfTheMessage(msg.getId());
					    msg.setReplies(rlist);
					}
						
					mav.addObject("messages",msgList);
					mav.setViewName("displaySentMsg");
					}catch(Exception e)
					{
						System.out.println(e);
						mav.setViewName("error");
					}
					return mav;
				}
			
		
		
		
		
	//method to fetch all the professionals
	@RequestMapping("/sendMessage.spring")
	public ModelAndView sendMsg(
		@ModelAttribute	Message msg)
	{
		ModelAndView mav=new ModelAndView();
		try
		{
			//Fetching current system date
			Date d=new Date();
			//Creating a DateFormat object 
			DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
			//Setting the formatted date to the msg
			msg.setMsgDate(df.format(d));
			//Getting the msg saved
			mdao.save(msg);
			mav.addObject("message","Your question is successfully submitted.");
			mav.setViewName("userNotification");
			
		}catch(Exception e)
		{
			System.out.println(e);
			mav.setViewName("error");
		}
		return mav;
		
	}
	
	//method to save a reply
		@RequestMapping("/sendReply.spring")
		public ModelAndView sendReply(
			@ModelAttribute	Reply reply)
		{
			ModelAndView mav=new ModelAndView();
			try
			{
				//Fetching current system date
				Date d=new Date();
				//Creating a DateFormat object 
				DateFormat df=DateFormat.getDateInstance(DateFormat.SHORT);
				//Setting the formatted date to the msg
				reply.setDate(df.format(d));
				
				//Getting the msg saved
				mdao.saveReply(reply);
				mav.addObject("message","Your reply is successfully submitted.");
				mav.setViewName("userNotification");
				
			}catch(Exception e)
			{
				System.out.println(e);
				mav.setViewName("error");
			}
			return mav;
			
		}
		//method to save a reply
				@RequestMapping("/reply.spring")
				public ModelAndView viewReplies(
					@RequestParam	int msgId)
				{
					ModelAndView mav=new ModelAndView();
					try
					{
						//Fetching message using its Id
						Message msg=mdao.getMsgById(msgId);
						msg.setSenderName(udao.getName(msg.getSender()));
						//Fetching replies of the Msg
						List<Reply> replies=mdao.repliesOfTheMessage(msgId);
						mav.addObject("message",msg);
						mav.addObject("replies",replies);
						mav.setViewName("msgWithReplies");
						
					}catch(Exception e)
					{
						System.out.println(e);
						mav.setViewName("error");
					}
					return mav;
					
				}
		
}
