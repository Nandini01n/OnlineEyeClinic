package com.cg.onlineeyeclinic.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineeyeclinic.exception.SpectaclesIdNotFoundException;
import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.repository.ISpectaclesRepository;

/**
 * SpectaclestServiceImpl class provides methods to perform CRUD operations on Spectacles
 * 
 * @author M Sneha
 */

@Service("name= SpectaclesService")

@Transactional
public class SpectaclesServiceImpl implements ISpectaclesService {

	private static final String ERROR_MESSAGE = "Spectacles ID Not Found";

	@Autowired
	private ISpectaclesRepository spectaclesRepository;
	
	/**
	 * This method Adds Spectacles by calling the save method
	 * 
	 * @param spectacles - spectacles entity details
	 * @return spectacles - spectacles entity details
	 * 
	 */


	@Override
	public Spectacles addSpectacles(Spectacles spectacles) {
		return spectaclesRepository.save(spectacles);
	}
	
	/**
	 * This method Updates Spectacles by calling the save method
	 * 
	 * @param spectacles - spectacles entity details
	 * @return spectacles -spectacles entity details
	 * 
	 */


	@Override
	public Spectacles updateSpectacles(Spectacles spectacles) {
		Integer spectaclesId = spectacles.getSpectaclesId();
		Optional<Spectacles> specs = spectaclesRepository.findById(spectaclesId);
		if (specs.isEmpty())
			throw new SpectaclesIdNotFoundException(ERROR_MESSAGE);
		return spectaclesRepository.save(spectacles);
	}
	
	/**
	 * This method Deletes Report by calling the deleteById method
	 * 
	 * @param spectaclesId - spectacles Id
	 * @return null
	 * 
	 */


	@Override
	public Spectacles deleteSpectacles(Integer spectaclesId) {
		Optional<Spectacles> specs = spectaclesRepository.findById(spectaclesId);
		if (specs.isEmpty())
			throw new SpectaclesIdNotFoundException(ERROR_MESSAGE);
		spectaclesRepository.deleteById(spectaclesId);
		return null;
	}
	
	/**
	 * This method takes Spectacles Id and returns the Spectacles based on Id
	 * 
	 * @param spectaclestId - integer value to display spectacles based on spectaclesId
	 * @return spectacles - spectacles entity details
	 */


	@Override
	public Optional<Spectacles> viewSpectacles(Integer spectaclesId) {
		Optional<Spectacles> specs = spectaclesRepository.findById(spectaclesId);
		if (specs.isEmpty())
			throw new SpectaclesIdNotFoundException(ERROR_MESSAGE);
		return specs;

	}
	/**
	 * This method returns the Spectacles List present in the Spectacles Table
	 * 
	 * If Spectales list is null, returns null
	 * 
	 * @return list of spectacles
	 * 
	 */


	@Override
	public List<Spectacles> viewSpectaclesList() {
		return spectaclesRepository.findAll();
	}

}