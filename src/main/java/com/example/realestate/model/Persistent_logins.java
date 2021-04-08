package com.example.realestate.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Persistent_logins {
    @NotNull
    @Column(columnDefinition = "varchar(64)")
    private String username;

    @Id
    @Column(columnDefinition = "varchar(64)")
    private String series;

    @NotNull
    private String token;

    @NotNull
    private Timestamp last_used;
}
