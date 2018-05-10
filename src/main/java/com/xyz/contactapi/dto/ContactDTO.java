package com.xyz.contactapi.dto;
import java.sql.Clob;
import java.sql.Date;

import com.xyz.contactapi.dto.entity.Address;
import com.xyz.contactapi.dto.entity.PhoneNumber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private  Long id;
	
    private String name;
    
    private String company;
    
   /* private Clob image;*/
    
    private String email;
    
    private Date birthdate;
    
    private PhoneNumber phone;
    
    private Address address;
    
    
}

