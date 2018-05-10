package com.xyz.contactapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "phoneNumber")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false)
	Long phoneId;
	
	@Column
	String work;
	
	@Column
	String personal;

}
