package com.npcweb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.npcweb.domain.Post;

public interface BoardDAO {
	//목록보기
	List<Post> getAllPost(long board_id) throws DataAccessException;
}
