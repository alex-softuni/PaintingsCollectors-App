package com.paintingscollectors.user.model;

import com.paintingscollectors.painting.model.FavoritePaintings;
import com.paintingscollectors.painting.model.Painting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Painting> paintings;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FavoritePaintings> favoritePaintings;

}
