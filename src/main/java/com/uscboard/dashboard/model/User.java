package com.uscboard.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import net.bytebuddy.agent.builder.AgentBuilder.Listener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "Gender is mandatory")
    private String Gender;
    @Email(message = "Email is mandatory and must be valid")
    private String email;
    @NotBlank(message = "user password is mandatory")
    private String password;
    @NotEmpty
    private int active;
    @NotEmpty
    private String roles = "";
    @NotEmpty
    private String permisions = "";

    public User(String email, String password, String roles, String permissions){
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.permisions = permissions;
        this.active = 1;
    }

    protected User(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return Gender;
    }
    public void setGender(String gender) {
        Gender = gender;
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
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getPermisions() {
        return permisions;
    }
    public void setPermisions(String permisions) {
        this.permisions = permisions;
    }
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    public List<String> getPermissionList(){
        if(this.permisions.length() > 0){
            return Arrays.asList(this.permisions.split(","));
        }
        return new ArrayList<>();
    }
}
