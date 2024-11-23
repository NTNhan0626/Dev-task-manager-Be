package com.haku.devtask_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Newspaper {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;


    public Newspaper() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
