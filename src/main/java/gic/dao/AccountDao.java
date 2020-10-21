package gic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gic.dto.AccountDto;
import gic.mapper.AccountMapper;

@Repository
public class AccountDao {
	
	@Autowired
	private AccountMapper mapper;
	
	public List<AccountDto> getAccountList() {
		return mapper.getAccountList();
	}
	
	public void register(AccountDto accountDto) {
		mapper.register(accountDto);
	}
	
	public AccountDto edit(int accountId) {
		return mapper.getAccount(accountId);
	}
	
	public void update(AccountDto accountDto) {
		mapper.update(accountDto);
	}

	public void delete(int accountId) {
		mapper.delete(accountId);
	}
}
