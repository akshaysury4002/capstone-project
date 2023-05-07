package com.as.project.domain;




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    @Column(name = "id")
    private long id;

    @Column(name = "uname",nullable = false)
    private String uname;

    @Column(unique = true,nullable = false, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role") 
    private String role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_booking",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "booking_id")
    )
    private List<Bookings> bookings;

    
}
