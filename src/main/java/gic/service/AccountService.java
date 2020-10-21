package gic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gic.dao.AccountDao;
import gic.dto.AccountDto;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountDao dao;
	
	public List<AccountDto> getAccountList() {
		return dao.getAccountList();
	}
	
	public void register(AccountDto accountDto) {
		dao.register(accountDto);
	}
	
	public AccountDto edit(int accountId) {
		return dao.edit(accountId);
	}
	
	public void update(AccountDto accountDto) {
		dao.update(accountDto);
	}

	public void delete(int accountId) {
		dao.delete(accountId);
	}
}
