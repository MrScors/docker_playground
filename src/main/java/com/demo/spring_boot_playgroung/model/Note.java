package com.demo.spring_boot_playgroung.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Note {

    @Id
    @NonNull
    private Long id;
    private String header;
    private String body;

}