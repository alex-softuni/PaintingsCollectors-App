package com.paintingscollectors.painting.repository;

import com.paintingscollectors.painting.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaintingRepository extends JpaRepository<Painting, UUID> {

}
