package com.xyz.contactapi.dto.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    //Contact getOne(Long aLong);
	
	@Query("select c from Contact c where c.email= ?1")
	Contact findByEmail(String email);
	
	@Query("select c from Contact c where c.email= ?1")
	Contact updateContact(String email);
	
	@Query("select c from Contact  c\r\n" + 
			"where (c.phone.personal = ?1 or c.phone.work = ?1)")
	Contact findByPhoneNumber(String phoneNumber);
	
}
