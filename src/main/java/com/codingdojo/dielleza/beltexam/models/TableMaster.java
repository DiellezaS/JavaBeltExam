package com.codingdojo.dielleza.beltexam.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="tables")
public class TableMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message=" Name is required!")
    @Size(min=2, max=30, message="First Name must be between 3 and 30 characters")
    private String name;

    @NotNull
    @Min(1)
    @Max(10)
    private int numGuest;


    @Size( max=250, message="Notes must be max 250 characters")
    private String notes;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tablemaster_id")
    private User lead;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_tables",
            joinColumns = @JoinColumn(name = "tablemaster_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    public TableMaster() {
    }

    public TableMaster(Long id, String name, int numGuest, Date createdAt, Date updatedAt, String notes, User lead, List<User> users) {
        this.id = id;
        this.name = name;
        this.numGuest = numGuest;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lead = lead;
        this.users = users;
        this.notes=notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getLead() {
        return lead;
    }
    public int getNumGuest() {
        return numGuest;
    }

    public void setNumGuest(int numGuest) {
        this.numGuest = numGuest;
    }


    public void setLead(User lead) {
        this.lead = lead;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    //empty constructor

    //constuctor

    //getters setters


    //getters setters edhe per modelin e lidhur si many to one

}
