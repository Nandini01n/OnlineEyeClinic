package com.cg.onlineeyeclinic.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.service.ISpectaclesService;

@RestController
//@RequestMapping("/api/spectacles")
@Validated
public class SpectaclesController {

	@Autowired
	private ISpectaclesService spectaclesService;
    //ADD SPECTACLES
	@PostMapping("/add_spectacles")
	public ResponseEntity<Spectacles> addSpectacles(@Valid @RequestBody Spectacles spectacles) {
		spectacles = spectaclesService.addSpectacles(spectacles);
		return new ResponseEntity<>(spectacles, HttpStatus.OK);
	}
	//UPDATE SPECTACLES
	@PutMapping("/update_spectacles")
	public ResponseEntity<Spectacles> updateSpectacles(@Valid @RequestBody Spectacles spectacles) {
		spectacles = spectaclesService.updateSpectacles(spectacles);
		return new ResponseEntity<>(spectacles, HttpStatus.OK);
	}
    //DELETE SPECTACLES
	@DeleteMapping("/delete_spectacles_by_id/{spectaclesId}")
	public ResponseEntity<String> deleteSpectaclesById(@PathVariable("spectaclesId") Integer spectaclesId) {
		spectaclesService.deleteSpectacles(spectaclesId);
		return new ResponseEntity<>("spectacles Deleted Successfully", HttpStatus.OK);
	}
	//VIEW SPECTACLES BY ID
	@GetMapping("/view_spectacles_by_id/{spectaclesId}")
	public ResponseEntity<Optional<Spectacles>> viewSpectaclesById(@PathVariable("spectaclesId") Integer spectaclesId) {
		Optional<Spectacles> specs = spectaclesService.viewSpectacles(spectaclesId);
		return new ResponseEntity<>(specs, HttpStatus.OK);
	}
	//VIEW ALL SPECTACLES
	@GetMapping("/view_all_spectacles")
	public ResponseEntity<List<Spectacles>> viewAllSpectacles() {
		return new ResponseEntity<>(spectaclesService.viewSpectaclesList(), HttpStatus.OK);
	}
}