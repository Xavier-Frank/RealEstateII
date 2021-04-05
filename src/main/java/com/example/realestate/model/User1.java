package com.example.realestate.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Entity
@Table(name = "user1")
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String firstname;
    @NotEmpty
    @Column(nullable = false, length = 20)
    private String lastname;
    @NotEmpty
    @Column(length = 13)
    private String phonenumber;


    //problem
    @Column(nullable =  false, columnDefinition = "varchar(750)")
    private String password;

    public User1(Long id, String email, String firstname, String lastname, String phonenumber, String password) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public User1() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

