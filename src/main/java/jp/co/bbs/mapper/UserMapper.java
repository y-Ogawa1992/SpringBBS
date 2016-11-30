package jp.co.bbs.mapper;

import java.util.List;

import jp.co.bbs.dto.user.UserDto;
import jp.co.bbs.entity.User;

public interface UserMapper {
	User getUser(int id);
	List<User> getUserAll();
	int updateUser(UserDto dto);
}
