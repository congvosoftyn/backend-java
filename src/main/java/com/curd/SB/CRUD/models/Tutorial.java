package com.curd.SB.CRUD.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "tutorials")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column()
    private String description;

    @Column()
    private boolean published;

    public Tutorial() {
    }

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

}
