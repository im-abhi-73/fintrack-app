package com.abhee.company.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhee.company.dto.AuthDTO;
import com.abhee.company.dto.ProfileDTO;
import com.abhee.company.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {
	
	private final ProfileService profileService;
	
	
	@PostMapping("/register")
	public ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO){
		
		ProfileDTO registerProfile = profileService.registerProfile(profileDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerProfile);	
	}
	
	
	@GetMapping("/activate")
	public ResponseEntity<String>activateProfile(@RequestParam String token){
		
		boolean isActivated = profileService.activateProfile(token);
		
		if(isActivated) {
			return ResponseEntity.ok("Profile activated successfully.");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation Token Not Found or Already Used.");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO){
			
		try {
			   if(!profileService.isAcountActive(authDTO.getEmail())) {				   
				   return ResponseEntity.status(HttpStatus.FORBIDDEN).body( Map.of("message","Acount is not active. Please activate your account first."));   
			   }
			   
			   Map<String, Object> response = profileService.authenticateAndGeneratedToken(authDTO);

			   return ResponseEntity.ok(response);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message",e.getMessage()));
		}
	}

	

	
	
	
	
	
	
	
	
	
	
}
