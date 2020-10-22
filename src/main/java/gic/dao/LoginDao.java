package gic.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gic.dto.AccountDto;
import gic.mapper.LoginMapper;

@Repository
public class LoginDao {
	
	@Autowired
	private LoginMapper loginMapper;
	
	public AccountDto getLoginInfo(String username) {
		return loginMapper.getLoginInfo(username);
	}
}
