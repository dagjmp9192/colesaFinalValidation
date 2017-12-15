package controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import daos.UserDao;
import models.User;
import util.FileManager;

@Controller
public class LawyersController {

	@Autowired
	UserDao udao;
	
	
	//method to fetch all the professionals
	@RequestMapping("/viewPro.spring")
	public ModelAndView editProfile()
	{
		ModelAndView mav=new ModelAndView();
		try
		{
			List<User> users=udao.getUsersByType(User.professional);
			mav.addObject("lawyers",users);
			mav.setViewName("lawyers");
			
		}catch(Exception e)
		{
			System.out.println(e);
			mav.setViewName("error");
		}
		return mav;
		
	}
	
	
		
}
