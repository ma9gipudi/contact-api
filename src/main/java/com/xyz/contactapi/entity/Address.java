package com.xyz.contactapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="Address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "address_Id")
	private Long addressId;
	
	@Column
	private String address1;
	
	@Column
	private String address2;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String zip;
	
}
