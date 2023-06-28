package com.codingdojo.dielleza.beltexam.models;



import java.util.Date;
import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="First Name is required!")
    @Size(min=2, max=30, message="First Name must be between 2 and 30 characters")
    private String firstName;


    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;

    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_tables",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tablemaster_id")
    )
    private List<TableMaster> tables;

    @Column(updatable=false)
    @OneToMany(mappedBy="lead", fetch = FetchType.LAZY)
    private List<TableMaster> tableLead;

    public User() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirm() {
        return confirm;
    }
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public User(Long id, String firstName, String email, String password, String confirm, List<TableMaster> tables, List<TableMaster> tableLead) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.tables = tables;
        this.tableLead = tableLead;
    }



    public List<TableMaster> getTables() {
        return tables;
    }

    public void setTables(List<TableMaster> tables) {
        this.tables = tables;
    }

    public List<TableMaster> getTableLead() {
        return tableLead;
    }

    public void setTableLead(List<TableMaster> tableLead) {
        this.tableLead = tableLead;
    }
}