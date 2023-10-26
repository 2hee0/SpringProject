package com.onehundredmillion.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.service.RentService;

@Controller
@RequiredArgsConstructor
public class RentController {
	
	private final RentService rentService;
	
	
}