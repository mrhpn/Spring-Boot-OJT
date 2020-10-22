package gic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gic.dto.AccountDto;

@Mapper
public interface LoginMapper {
	
	public AccountDto getLoginInfo(@Param("username") String username);
}
