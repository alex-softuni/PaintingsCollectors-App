package com.paintingscollectors.painting.model;

import com.paintingscollectors.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Style style;
    @Column(nullable = false)
    private String URL;
    @JoinColumn(nullable = false, name = "owner_id")
    @ManyToOne
    private User owner;
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer votes;
}
