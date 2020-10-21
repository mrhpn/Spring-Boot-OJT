package gic.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Login Failure Handler
 *
 * @author Min Aung Than Oo
 */
@Component
@Slf4j
public class LoginFailureHandler extends ExceptionMappingAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		setDefaultFailureUrl("/loginError");
		log.info("Authentication Failure : {}", String.valueOf(exception));
		Map<String, String> map = new HashMap<String, String>();
		setExceptionMappings(map);
		super.onAuthenticationFailure(request, response, exception);
	}
}
