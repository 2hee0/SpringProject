package com.onehundredmillion.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.onehundredmillion.library.service.LikeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;
	
	@PostMapping("/like")
    public void setLike(Model model) {
        
        likeService.setLike();
    }
	

}
