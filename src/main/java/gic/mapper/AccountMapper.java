package gic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.AccountDTO;

@Mapper
public interface AccountMapper {
	
	@Select("SELECT * FROM account")
	public List<AccountDTO> getAccountList();
}
