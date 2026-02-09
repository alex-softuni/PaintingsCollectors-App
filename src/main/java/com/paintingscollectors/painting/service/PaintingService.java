package com.paintingscollectors.painting.service;

import com.paintingscollectors.painting.model.FavoritePaintings;
import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.painting.repository.FavouritePaintingRepository;
import com.paintingscollectors.painting.repository.PaintingRepository;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.web.dto.AddPaintingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final FavouritePaintingRepository favouritePaintingRepository;

    @Autowired
    public PaintingService(PaintingRepository paintingRepository, FavouritePaintingRepository favouritePaintingRepository) {
        this.paintingRepository = paintingRepository;
        this.favouritePaintingRepository = favouritePaintingRepository;
    }

    public List<Painting> findAllPaintings() {
        List<Painting> allPaintings = paintingRepository.findAll();

        allPaintings.sort(Comparator.comparing(Painting::getVotes).reversed()
                .thenComparing(Painting::getName));

        return allPaintings;
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

    public void deleteById(UUID id) {
        paintingRepository.deleteById(id);
    }

    public void deleteFavouriteById(UUID id) {
        favouritePaintingRepository.deleteById(id);
    }

    public void addPaintingToFavourites(UUID id, User user) {
        Painting painting = paintingRepository.findById(id).orElseThrow(() -> new RuntimeException("Painting with id " + id + " not found"));
        FavoritePaintings favoritePaintings = FavoritePaintings.builder()
                .name(painting.getName())
                .author(painting.getAuthor())
                .URL(painting.getURL())
                .addedAt(LocalDateTime.now())
                .owner(user)
                .build();

        favouritePaintingRepository.save(favoritePaintings);
    }

    public void increaseVote(UUID id, User user) {
        Painting painting = paintingRepository.findById(id).orElseThrow(() -> new RuntimeException("Painting with id " + id + " not found"));
        painting.setVotes(painting.getVotes() + 1);

        paintingRepository.save(painting);
    }
}
