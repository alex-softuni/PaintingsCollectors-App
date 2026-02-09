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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        paintingService.addNewPainting(addPaintingRequest,user);


        return "redirect:/home";
    }
}
