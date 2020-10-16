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
}