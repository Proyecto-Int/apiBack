package com.puce.empat_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "canvas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostIt> postIts;
}
