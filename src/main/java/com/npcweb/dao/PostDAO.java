package com.npcweb.dao;

import org.springframework.dao.DataAccessException;

import com.npcweb.domain.Post;

public interface PostDAO {
	//create
	public void insertPost(Post post) throws DataAccessException;
	
	//read
	public Post readPost(long post_id) throws DataAccessException;
	
	//update
	public void updatePost(Post post) throws DataAccessException;
	
	//delete
	public void deletePost(Post post) throws DataAccessException;
}
