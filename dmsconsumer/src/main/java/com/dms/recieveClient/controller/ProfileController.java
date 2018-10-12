package com.dms.recieveClient.controller;

import com.dms.recieveClient.myService.CarService;
import com.dms.recieveClient.urlRepository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
	
	@Autowired
    ProfileRepository profileRepository;

	@Autowired
	CarService carService;


	@RequestMapping("/")
	public String home(){
		return "index";
	}
	
	@RequestMapping(value = "/userProfiles")
	public String profileList(Model model) {
		carService.getById(1);
		model.addAttribute("profiles", profileRepository.getAllProfiles());
		return "userProfiles";
	}
	
	@RequestMapping("/userDetails")
	public String profileDetails(@RequestParam("id") String userId, Model model) {
		model.addAttribute("profile", profileRepository.getProfile(userId));
		return "userDetails";
	}



}