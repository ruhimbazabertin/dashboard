package com.uscboard.dashboard.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.uscboard.dashboard.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {

    }

    public int getId() {

        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
       return this.name;
    }
    
    
}
