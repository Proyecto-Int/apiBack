package com.puce.empat_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "postit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostIt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quadrantId; // Identificador del cuadrante
    private String text;

    @ManyToOne
    @JoinColumn(name = "canvas_id", nullable = false)
    private Canvas canvas;

    private Integer x; // Posición X
    private Integer y; // Posición Y
}
