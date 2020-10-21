package gic.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Logout Handler
 *
 * @author Min Aung Than Oo
 */
@Slf4j
@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private HttpSession session;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		session.invalidate();
		if (null != authentication) {
			try {
				request.logout();
			} catch (ServletException e) {
				log.warn("Error occurred when logging out", e);
			}
		}
		RedirectStrategy redirectStrategy = getRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
		super.handle(request, response, authentication);
	}
}
