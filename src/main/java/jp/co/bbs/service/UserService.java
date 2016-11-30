package jp.co.bbs.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bbs.dto.user.UserDto;
import jp.co.bbs.entity.User;
import jp.co.bbs.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	//ユーザー管理画面にユーザーの一覧を表示させる
	public List<UserDto> getUserAll() {
		List<User> userList = userMapper.getUserAll();
		List<UserDto> resultList = convertToDto(userList);
		return resultList;
	}

	private List<UserDto> convertToDto(List<User> userList) {
		List<UserDto> resultList = new LinkedList<UserDto>();
		for (User entity : userList) {
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	//idを元にユーザーを探す
    public UserDto getUser(Integer id) {
    	UserDto dto = new UserDto();
        User entity = userMapper.getUser(id);
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

	//編集画面からのアップデート
	public int updateUser(UserDto dto) {
		int count = userMapper.updateUser(dto);
		return count;
	}
}
