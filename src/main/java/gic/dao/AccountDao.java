package gic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.AccountDTO;
import gic.mapper.AccountMapper;

@Repository
public class AccountDao {
	
	@Autowired
	private AccountMapper mapper;
	
	public List<AccountDTO> getAccountList() {
		return mapper.getAccountList();
	}
	
	public void register(AccountDTO accountDto) {
		mapper.register(accountDto);
	}
	
	public AccountDTO edit(int accountId) {
		return mapper.getAccount(accountId);
	}
	
	public void update(AccountDTO accountDto) {
		mapper.update(accountDto);
	}

	public void delete(int accountId) {
		mapper.delete(accountId);
	}
}
