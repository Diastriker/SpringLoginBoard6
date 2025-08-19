package com.board.users.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.users.domain.UserDTO;

// 이 인터페이스가 xml에 id=insertMenu랑 여기insertMenu 연결시키는 역할
@Mapper 
public interface UserMapper {
	List<UserDTO> getUserList();

	void insertUser(UserDTO userDTO);

	void deleteUser(String userid);

	UserDTO getUser(String userid);

	void updateUser(UserDTO userDTO);

	UserDTO login(String userid, String passwd);

}

