package springSecurity.resources;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/hello")
public class Resources {
	
	@GetMapping("/all")
	public String Hello(){
		return "Hello youTube";
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/all")
	public String securedHello(){
		return "Secured Hello";
		
	}

}
