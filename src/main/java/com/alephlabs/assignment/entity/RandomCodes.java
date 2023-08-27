package com.alephlabs.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity(name = "random_data")
public class RandomCodes implements Persistable<UUID>  {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "unique_code",unique = true,updatable = false)
    private String uniqueCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

//    prevent Spring Data doing a select-before-insert
//    this particular entity is never updated
    @Override
    public boolean isNew() {
        return true;
    }
}
