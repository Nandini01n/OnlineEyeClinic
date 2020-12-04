package com.cg.onlineeyeclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.onlineeyeclinic.model.Tests;
/** ITestRepository interface provides abstract methods to perform CRUD operations on Tests entity
 * 
 * @author Manasa Magasani
 * 
 */

@Repository(value = "testRepository")
public interface ITestRepository extends JpaRepository<Tests, Integer> {

}

