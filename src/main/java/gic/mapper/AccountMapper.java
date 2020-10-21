package gic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gic.dto.AccountDto;

@Mapper
public interface AccountMapper {
	
	public List<AccountDto> getAccountList();
	
	public void register(AccountDto accountDto);
	
	public AccountDto getAccount(int accountId);
	
	public void update(AccountDto accountDto);

	public void delete(int accountId);
}
