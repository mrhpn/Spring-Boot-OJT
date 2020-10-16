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
}
