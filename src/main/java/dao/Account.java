package dao;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		roles.add("USER");
		
		return roles;
	}
}
