package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // annotation to use session.
public class WelcomeController{
//	private AuthenticationService authen;
//	
//	public LoginController(AuthenticationService authen) {
//		super();
//		this.authen = authen;
//	}
	@RequestMapping(value="/",method=RequestMethod.GET)
	// In the controller, when you need anything available to jsp, then use modelmap
    public String gotoWelcomePage(ModelMap model){
		// SpringBoot default logging system Sl4j; 
		//Logger logger = LoggerFactory.getLogger(getClass());
		//logger.debug("Request Param is {}",name);
		//System.out.println("Request Parameter of type: "+name); // Not recommended though suggested
		// way is through logging.
		model.put("name",getLoggedInUsername());
    	return "welcome";
    }
	
	private String getLoggedInUsername(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
		
	}
	
	// This is the custom validation done by making use of AuthenticationService class.
	//we will handle it by spring security now..
//	@RequestMapping(value="login",method=RequestMethod.POST)
//    public String gotoWelcomePage(@RequestParam String name, @RequestParam String password,
//    		ModelMap model){
//		if(authen.authenticate(name, password)){
//			model.put("name",name);
//			//model.put("password",password);
//	    	return "welcome";
//		}
//		model.put("errorMessage","Invalid Credentials! Please try again..");
//		return "login";
//    }
}
