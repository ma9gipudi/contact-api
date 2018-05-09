package com.xyz.contactapi.dto.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Entity()
@Table(name = "contact")
public class Contact {

    @Column(nullable = false)
    @Id
    public long id;

    @Column
    private String name;
}
