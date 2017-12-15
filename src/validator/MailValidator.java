package validator;

import models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import daos.UserDao;


@Component
public class MailValidator implements Validator
{
	//dependency of the validator
	@Autowired
	UserDao udao;
	

	@Override
	public boolean supports(Class<?> cls) {
		
		return User.class.equals(cls);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user=(User)target;
		if(udao.IsMailIdExists(user.getEmail()))
			errors.rejectValue("email","error.email", "MailId already registered.");
		
	}

	
}
