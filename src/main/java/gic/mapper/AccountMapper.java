package gic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dto.AccountDTO;

@Mapper
public interface AccountMapper {
	
	public List<AccountDTO> getAccountList();
	
	public void register(AccountDTO accountDto);
	
	public AccountDTO getAccount(int accountId);
	
	public void update(AccountDTO accountDto);

	public void delete(int accountId);
}
