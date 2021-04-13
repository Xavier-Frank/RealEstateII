package com.example.realestate.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.sql.Date;

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
    @NotEmpty
    @Column(nullable =  false, columnDefinition = "varchar(750)")
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "last_login")
    private Date lastLogin;
    @Column(name = "reset_token")
    private String resetToken;

    //constructor

    public User1(Long id, @NotEmpty String email, @NotEmpty String firstname, @NotEmpty String lastname, @NotEmpty String phonenumber, @NotEmpty String password, boolean enabled, Date createdOn, Date lastLogin, String resetToken) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.password = password;
        this.enabled = enabled;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
        this.resetToken = resetToken;
    }

    public User1() {
    }

    //getters and setters

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}

