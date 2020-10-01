package com.fernando.bookstore.data.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public abstract class DefaultEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @JsonIgnore
    @CreatedDate
    private Date createdAt;

    @JsonIgnore
    @LastModifiedDate
    private Date updatedAt;

}
