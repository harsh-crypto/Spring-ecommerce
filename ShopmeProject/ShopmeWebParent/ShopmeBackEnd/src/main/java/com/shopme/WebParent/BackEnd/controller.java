package com.shopme.WebParent.BackEnd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

	@GetMapping("/index")
	public String getindex() {
		return "index";
	}
	
	@GetMapping("/home")
	public String gethome() {
		return "Home";
	}
	
}
