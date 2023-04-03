package Base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	 
	@RequestMapping("/welcome")
	public String welcome()
	{
		String text = "This is private age";
		text+="This page is not allowed";
		return text;
		
	}
	
	
	
	

}
