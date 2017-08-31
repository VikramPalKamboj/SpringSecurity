package springSecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springSecurity.model.CustomUserDetails;
import springSecurity.model.Users;
import springSecurity.repositroy.Repositroy;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	Repositroy userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		
		Optional<Users> optionalUser=userRepository.findByName(userName);
		
		optionalUser.orElseThrow(()-> new UsernameNotFoundException("User Name not found"));
		
		return optionalUser.map(
			CustomUserDetails::new).get();
		
	}

}
