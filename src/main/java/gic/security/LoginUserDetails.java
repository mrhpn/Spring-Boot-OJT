package gic.security;

import lombok.Getter;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import gic.dto.AccountDto;

/**
 * User detailed information class for login authentication
 *
 * @author Min Aung Than Oo
 */
@Getter
public class LoginUserDetails extends User {

	/** serialVersionUID */
	private static final long serialVersionUID = -3927819677897821853L;

	/** Role */
	private String role;

	/**
	 * User ID
	 */
	private Integer userId;

	/**
	 * @param user Account Information
	 */
	public LoginUserDetails(AccountDto user) {
		super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + user.getRole()));
		userId = user.getId();
		role = user.getRole();
		System.out.println(user.getId() + " <---");
	}

	public boolean isSysAdmin() {
		return role.equals("ADMIN");
	}

	public boolean isOperator() {
		return role.equals("USER");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LoginUserDetails.class.getSimpleName()).append('(').append("Role:").append(role).append(',')//
				.append("name:").append(getUsername()).append(',').append(')');
		return sb.toString();
	}
}
