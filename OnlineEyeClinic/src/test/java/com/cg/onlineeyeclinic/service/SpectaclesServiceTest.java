package com.cg.onlineeyeclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineeyeclinic.exception.SpectaclesIdNotFoundException;
import com.cg.onlineeyeclinic.model.Spectacles;
import com.cg.onlineeyeclinic.repository.ISpectaclesRepository;

@TestInstance(Lifecycle.PER_CLASS)
 class SpectaclesServiceTest {
	@InjectMocks
	private SpectaclesServiceImpl spectaclesService;
	
	@Mock
	private ISpectaclesRepository spectaclesRepository;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	 void addSpectaclesTest() {
		Spectacles specs = new Spectacles(01,"car","rect",540.0);
		
		when(spectaclesRepository.save(specs)).thenReturn(specs);
		
		specs = spectaclesService.addSpectacles(specs);
		
		assertEquals("rect", specs.getSpectaclesDescription());
		verify(spectaclesRepository, times(1)).save(specs);
	}
	
	@Test
	 void updateDoctorTest() throws SpectaclesIdNotFoundException {
		
		Spectacles specs = new Spectacles(01,"car","rect",540.0);
		
		Integer spectaclesId = specs.getSpectaclesId();
		Optional<Spectacles> spectacles = spectaclesRepository.findById(spectaclesId);
		
		when(spectaclesRepository.findById(spectaclesId)).thenReturn(spectacles);
		when(spectaclesRepository.save(specs)).thenReturn(specs);
	
		specs.setSpectaclesCost(450.0);
		
		specs= spectaclesService.updateSpectacles(specs);
		
		assertEquals(450.0,specs.getSpectaclesCost());
		verify(spectaclesRepository, times(1)).save(specs);
	}
	
	@Test
   void deleteDoctorTest() throws SpectaclesIdNotFoundException {
		Spectacles specs= new Spectacles(01,"car","rect",540.0);
		Integer spectaclesId = specs.getSpectaclesId();
	
		Optional<Spectacles> spectacles = Optional.of(specs);
		
		when(spectaclesRepository.findById(spectaclesId)).thenReturn(spectacles);
		specs= spectaclesService.deleteSpectacles(spectaclesId);
		
		assertNull(specs);
		verify(spectaclesRepository, times(1)).deleteById(spectaclesId);
	}
	
	@Test
	 void viewDoctorTest() throws SpectaclesIdNotFoundException {
		Spectacles specs= new Spectacles(01,"car","rect",540.0);
		Integer spectaclesId = specs.getSpectaclesId();
	

		Optional<Spectacles> spectacles = Optional.of(specs);
		
		when(spectaclesRepository.findById(spectaclesId)).thenReturn(spectacles);
		Optional<Spectacles> viewSpectacles = spectaclesService.viewSpectacles(spectaclesId);
		
		assertEquals("car",viewSpectacles.get().getSpectaclesModel());
		verify(spectaclesRepository, times(2)).findById(spectaclesId);
	}
	
	@Test
	 void viewSpectaclesListTest() {
		List<Spectacles> spectaclesList = new ArrayList<Spectacles>();
		spectaclesList.add(new Spectacles(01,"car","rect",540.0));
		spectaclesList.add(new Spectacles(02,"rail","square",640.0));
		spectaclesList.add(new Spectacles(03,"bus","semisq",940.0));
		
		when(spectaclesRepository.findAll()).thenReturn(spectaclesList);
		
		List<Spectacles> specsList = spectaclesService.viewSpectaclesList();
		
		assertEquals(3, specsList.size());
		verify(spectaclesRepository, times(1)).findAll();
	}

	
}
