package gic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import gic.dto.AccountDto;
import gic.service.AccountService;
import gic.service.LoginService;

/**
 * User information acquisition service for login authentication.
 *
 * @author Min Aung Than Oo
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginService loginService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AccountDto retDto = loginService.getLoginInfo(userName);
		/*AccountDto retDto = new AccountDto();
		retDto.setId(1);
		retDto.setRole("ADMIN");
		retDto.setName("admin");
		retDto.setPassword("$2a$10$2X/kuiqabNRE2eTn77zWa.6RG2k/qEbNljR8/OnmKK1p9/PUOVytK");*/
		if (retDto == null) {
			throw new UsernameNotFoundException("User not found for name: " + userName);
		}
		return new LoginUserDetails(retDto);
	}

}
