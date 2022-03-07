package com.pointwest.pastebook.pastebook_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
// Designate the table name related to the model
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column
    private Date birthdate;
    @Column
    private String password;
    @Column
    private String gender;
    @Column
    private String mobileNumber;
    @Column
    private boolean isOnline;
    @Column
    private Date date_created;


    public User(){

    }


}
