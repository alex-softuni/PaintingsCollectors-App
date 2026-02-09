package com.paintingscollectors.painting.service;

import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.painting.repository.PaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintingService {

    private final PaintingRepository paintingRepository;

    @Autowired
    public PaintingService(PaintingRepository paintingRepository) {
        this.paintingRepository = paintingRepository;
    }

    public List<Painting> findAllPaintings() {
        return paintingRepository.findAll();
    }
}
