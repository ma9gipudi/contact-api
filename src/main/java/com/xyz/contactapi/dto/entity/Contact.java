package com.xyz.contactapi.dto.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Entity()
@Table(name = "contact")
public class Contact  implements Serializable{

	private static final long serialVersionUID = 1L;

	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    
    @Column
    private String company;
    
/*    @Column
    private Clob image;
*/    
    
    @Column
    private Date birthdate;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_Id" )
    private PhoneNumber phone;
    
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    private Address address;

}
