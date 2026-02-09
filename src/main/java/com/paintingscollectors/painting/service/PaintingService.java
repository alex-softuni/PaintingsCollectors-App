package com.paintingscollectors.painting.service;

import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.painting.repository.PaintingRepository;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.web.dto.AddPaintingRequest;
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

    public void addNewPainting(AddPaintingRequest addPaintingRequest, User user) {
        Painting painting = Painting.builder()
                .name(addPaintingRequest.getName())
                .author(addPaintingRequest.getAuthor())
                .URL(addPaintingRequest.getURL())
                .style(addPaintingRequest.getStyle())
                .owner(user)
                .votes(0)
                .build();

        paintingRepository.save(painting);
    }
}
