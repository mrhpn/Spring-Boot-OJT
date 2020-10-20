package gic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.AccountDTO;
import gic.dao.AccountDao;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountDao dao;
	
	public List<AccountDTO> getAccountList() {
		return dao.getAccountList();
	}
	
	public void register(AccountDTO accountDto) {
		dao.register(accountDto);
	}
	
	public AccountDTO edit(int accountId) {
		return dao.edit(accountId);
	}
	
	public void update(AccountDTO accountDto) {
		dao.update(accountDto);
	}

	public void delete(int accountId) {
		dao.delete(accountId);
	}
}
