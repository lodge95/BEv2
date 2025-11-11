package com.mybizexchange.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Listing {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 4000)
    private String description;

    private String industry;

    private Double price;

    private String location;

    private Boolean published = true;

    // path to image assets (stubbed)
    private String imagePath;

    // owner
    @ManyToOne
    private User owner;
}
