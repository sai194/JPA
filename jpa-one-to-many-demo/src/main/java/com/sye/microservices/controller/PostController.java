package com.sye.microservices.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sye.microservices.domain.Comment;
import com.sye.microservices.domain.Post;
import com.sye.microservices.repository.PostRepository;
import com.sye.microservices.util.ResourceNotFoundException;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    // /posts?page=0&size=2&sort=createdAt,desc
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
    
    public void createPostAndComments() {
    	// Create a Post
    	Post post = new Post("post test", "post description", "post content");

    	// Create Comments
    	Comment comment1 = new Comment("Great Post!");
    	comment1.setPost(post);
    	Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
    	comment2.setPost(post);

    	// Add comments in the Post
    	post.getComments().add(comment1);
    	post.getComments().add(comment2);
    	
    	// Save Post and Comments via the Post entity
    	post = postRepository.save(post);
    	System.out.println(post.getId());
    	// Retrieve Post
    	Post tempPost = postRepository.findById(post.getId()).orElse(null);
    	System.out.println(tempPost);

    	// Get all the comments
    	Set<Comment> comments = tempPost.getComments();
    	System.out.println(comments);
    }

}