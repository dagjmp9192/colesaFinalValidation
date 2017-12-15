package controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import validator.MailValidator;
import daos.UserDao;
import models.User;
import util.FileManager;

@Controller
public class UserController {

	@Autowired
	UserDao udao;
	@Autowired
	HttpSession session;
	@Autowired
	MailValidator validator;
	
	//method to display edit profile form
	@RequestMapping("/editProfile.spring")
	public String editProfile()
	{
		return "editProfile";
		
	}
	
	//to upload profile image
		@RequestMapping(value="/uploadImg.spring",
				method = RequestMethod.POST)
		public ModelAndView upload(
		@RequestParam("profileImage") MultipartFile file)
				throws Exception
		{
			
			
			ModelAndView mav=new ModelAndView("userNotification");
			
			if(!file.isEmpty())
			{
				User u=(User)session.getAttribute("user");
				String path=FileManager.save(file,u);
				if(path.length()>0)
				{
				udao.updateProfileImage(u.getId(),path);
				u.setImageUrl(path);
				mav.addObject("message","Your profile image successfully uploaded.");
				}
				
					
			}
			
			return mav; 
		}
	
	
	
	//method to process signup request
	@RequestMapping("/register.spring")
	public ModelAndView register(
		@ModelAttribute	@Valid User user,
		BindingResult br)
	{
		ModelAndView mav=new ModelAndView();
		try
		{
			//perform custom validation
			validator.validate(user, br);
			if(br.hasErrors())
				mav.setViewName("regForm");
			else
			{	
			udao.save(user);
			mav.setViewName("registered");
			}
		}catch(Exception e)
		{
			System.out.println(e);
			mav.setViewName("regFailed");
			
		}
		return mav;
	}
	//to display visitor home page 
		@RequestMapping("/vhome.spring")
		public ModelAndView signupForm()throws Exception
		{
			ModelAndView mav=new ModelAndView();
			mav.setViewName("vhome");
			mav.addObject("home",true);
			mav.addObject("user", new User());
			return mav; 
		}
		
	//method to update user profile
		@RequestMapping("/updateProfile.spring")
		public ModelAndView updateProfile(
			@ModelAttribute	User user)
		{
			ModelAndView mav=new ModelAndView();
			
			try
			{
				User u=(User)session.getAttribute("user");
				user.setUserType(u.getUserType());
				udao.updateProfile(user);
				mav.addObject("message","Your profile is successfully updated.");
				mav.setViewName("userNotification");
				//user object is replaced in the session
				session.setAttribute("user",user);
			}catch(Exception e)
			{
				System.out.println(e);
				mav.setViewName("error");
			}
			return mav;
		}
	//method to process logout request
		@RequestMapping("/logout.spring")
		public String logout()
		{
			session.invalidate();
			return "loggedOut";
		}
	
	//method to process login request
		@RequestMapping("/login.spring")
		public String authenticate(
			@ModelAttribute	User user)
		{
			String str="relogin";
			try
			{
				if(udao.find(user))
				{
				str="home";
				session.setAttribute("user",user);
				}
				
			}catch(Exception e)
			{
				System.out.println(e);
				
			}
			return str;
		}
		
		//Method to display user's profile
		@RequestMapping("/profile.spring")
		public String viewProfile()
		{
			return "profile";
		}
		
}
