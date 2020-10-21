package gic.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import gic.security.LoginFailureHandler;
import gic.security.LogoutHandler;

/**
 * Spring Security Setting
 *
 * @author Min Aung Than Oo
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	private LogoutHandler logoutHandler;

	/**
	 * Implement userDetailsService and Authentication
	 *
	 * @param auth AuthenticationManagerBuilder
	 * @throws Exception Exception
	 */
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		auth.authenticationProvider(provider);
	}

	/**
	 * Setting for Request
	 *
	 * @param http HttpSecurity
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config = http.authorizeRequests();

		config.antMatchers("/css/**", "/js/**", "/login", "/loginError", "/logOut").permitAll()
		.antMatchers("/gic/**").hasAnyRole("ADMIN", "USER").anyRequest().authenticated();

		http.formLogin().loginProcessingUrl("/").loginPage("/login").failureHandler(loginFailureHandler).defaultSuccessUrl("/")
				.usernameParameter("name").passwordParameter("password");

		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logOut")).logoutSuccessUrl("/login")
				.logoutSuccessHandler(logoutHandler);

		http.sessionManagement().maximumSessions(1).expiredUrl("/logOut");
		
		String csrfRegx = "/|/login|";

		http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
			private RegexRequestMatcher disableCsrfMatcher = new RegexRequestMatcher(csrfRegx, null);

			@Override
			public boolean matches(HttpServletRequest req) {
				if (disableCsrfMatcher.matches(req)) {
					return false;
				}
				if (HttpMethod.GET.name().equals(req.getMethod())) {
					return false;
				}
				return true;
			}
		});

		// Invalidate browser cache
		http.headers().cacheControl();
	}
}
