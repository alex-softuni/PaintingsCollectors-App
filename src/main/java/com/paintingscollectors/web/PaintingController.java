package com.paintingscollectors.web;

import com.paintingscollectors.painting.service.PaintingService;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.user.service.UserService;
import com.paintingscollectors.web.dto.AddPaintingRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/paintings")
public class PaintingController {

    private final UserService userService;
    private final PaintingService paintingService;

    @Autowired
    public PaintingController(UserService userService, PaintingService paintingService) {
        this.userService = userService;
        this.paintingService = paintingService;
    }

    @GetMapping("/add-new")
    public ModelAndView getAddPaintingPage(HttpSession httpSession) {

        ModelAndView mav = new ModelAndView();
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        User user = userService.getUserById(userId);

        mav.setViewName("new-painting");
        mav.addObject("user", user);
        mav.addObject("addPaintingRequest", new AddPaintingRequest());


        return mav;
    }

    @PostMapping("/add-new")
    public String addPainting(@Valid AddPaintingRequest addPaintingRequest, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "new-painting";
        }
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        User user = userService.getUserById(userId);

        paintingService.addNewPainting(addPaintingRequest, user);

        return "redirect:/home";
    }

    @PostMapping("/make-favourite/{id}")
    public String makeFavourite(@PathVariable UUID id, HttpSession httpSession) {
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        User user = userService.getUserById(userId);

        paintingService.addPaintingToFavourites(id, user);

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePainting(@PathVariable UUID id) {

        paintingService.deleteById(id);
        return "redirect:/home";
    }

    @DeleteMapping("/delete-favourite/{id}")
    public String deleteFavPainting(@PathVariable UUID id) {

        paintingService.deleteFavouriteById(id);
        return "redirect:/home";
    }

    @PostMapping("/votes/{id}")
    public String increaseVotes(@PathVariable UUID id, HttpSession httpSession) {

        UUID userId = (UUID) httpSession.getAttribute("user_id");
        User user = userService.getUserById(userId);

        paintingService.increaseVote(id, user);

        return "redirect:/home";
    }
}
