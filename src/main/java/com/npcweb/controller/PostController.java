package com.npcweb.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npcweb.dao.jpa.JpaPostDAO;
import com.npcweb.domain.Post;
import com.npcweb.service.PostService;

@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired JpaPostDAO postDao;
	@Autowired	PostService postService;

	//read
	@GetMapping("/{post_id}")
	public ResponseEntity<Post> readPost(@PathVariable long post_id) {
	    try {
	        Post post = postService.readPost(post_id);
	        if (post != null) {
	            return ResponseEntity.ok(post);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        // Handle the exception and return an error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	/*
	@GetMapping("/{post_id}")
	public ResponseEntity<Post> readPost(@PathVariable long post_id) {
		Post post = postService.readPost(post_id);
		if(post != null) {
			return ResponseEntity.ok(post);
		}else {
			return ResponseEntity.notFound().build();
		}


		PostRes res = new PostRes();
		res.setPostId(post.getPostId());
		res.setBoardId(post.getBoardId());
		res.setContent(post.getContent());
		res.setImportant(post.getImportant());
		res.setRangePost(post.getRangePost());
		res.setTitle(post.getTitle());
		res.setUserNo(post.getUserNo());

		model.put("post", res);

		return ResponseEntity.ok(model);

	}
	 */
	//create
	@PostMapping("/{board_id}")
	public void createPost(HttpServletRequest request, @PathVariable long board_id, @RequestBody PostReq req) {
		HttpSession session = (HttpSession) request.getSession();
		long userNo = (long) session.getAttribute("userno");

		System.out.println("create post "+req.toString());

		Post post = new Post();
		post.setBoardId(board_id);
		post.setContent(req.getContent());
		post.setCreateDate(new Date());
		post.setImportant(req.getImportant());
		post.setRangePost(req.getRangePost());
		post.setTitle(req.getTitle());
		post.setUserNo(userNo);

		postService.insertPost(post);
	}
	/*
	//update
	@PutMapping("/{post_id}")
	public void updatePost(@RequestBody PostReq req, @PathVariable long postId) {
		Post post = postService.readPost(postId);
		post.setPostId(req.getPostId());
		post.setBoardId(req.getBoardId());
		post.setContent(req.getContent());
		post.setImportant(req.getImportant());
		post.setUpdateDate(new Date());
		post.setRangePost(req.getRangePost());
		post.setTitle(req.getTitle());
		post.setUserNo(req.getUserNo());

		postService.updatePost(post);
	}

	//delete
	@DeleteMapping("/{post_id}")
	public void deletePost(@PathVariable long boardId, @PathVariable long post_id) {
		Post post = postService.readPost(post_id);
		postService.deletePost(post);
	}
	 */
}


class PostRes {
	private long postId, userNo, boardId;
	private String title, rangePost, content;
	private int important;
	private Date createDate;

	public PostRes(){}

	//getter
	public long getPostId() {
		return postId;
	}

	public long getUserNo() {
		return userNo;
	}

	public long getBoardId() {
		return boardId;
	}

	public String getTitle() {
		return title;
	}

	public String getRangePost() {
		return rangePost;
	}

	public String getContent() {
		return content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getImportant() {
		return important;
	}

	//setter
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRangePost(String rangePost) {
		this.rangePost = rangePost;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setImportant(int important) {
		this.important = important;
	}

	public void setCreateDate(Date date) {
		createDate = date;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}
}

class PostReq {
	private String title, content, rangePost;
	private long userNo, boardId;
	private int important, readCount;
	private Date create_date;

	public PostReq() {}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getRangePost() {
		return rangePost;
	}

	public long getUserNo() {
		return userNo;
	}

	public long getBoardId() {
		return boardId;
	}

	public int getImportant() {
		return important;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public int getReadCount() {
		return readCount;
	}

	@Override
	public String toString() {
		return "PostReq [title=" + title + ", content=" + content + ", rangePost=" + rangePost + ", userNo=" + userNo
				+ ", boardId=" + boardId + ", important=" + important + ", readCount=" + readCount + ", create_date="
				+ create_date + "]";
	}
}
