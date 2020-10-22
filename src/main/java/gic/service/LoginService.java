package gic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gic.dao.LoginDao;
import gic.dto.AccountDto;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	public AccountDto getLoginInfo(String username) {
		return loginDao.getLoginInfo(username);
	}
}
