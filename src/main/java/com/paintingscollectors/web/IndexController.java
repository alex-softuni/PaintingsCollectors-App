package com.paintingscollectors.web;

import com.paintingscollectors.exception.UserAlreadyExists;
import com.paintingscollectors.painting.model.Painting;
import com.paintingscollectors.user.model.User;
import com.paintingscollectors.user.service.UserService;
import com.paintingscollectors.web.dto.LoginRequest;
import com.paintingscollectors.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage() {

        return "index";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/register");
        mav.addObject("registerRequest", new RegisterRequest());

        return mav;
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(registerRequest);
        } catch (UserAlreadyExists e) {
            bindingResult.rejectValue("username", "UserAlreadyExists", e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("loginRequest", new LoginRequest());
        return mav;
    }

    @PostMapping("/login")
    public String processLoginRequest(
            @Valid LoginRequest loginRequest,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.loginUser(loginRequest);

        session.setAttribute("user_id", user.getId());

        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getUserById(userId);

        List<Painting> allPaintings = userService.getAllPaintings();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("user", user);
        mav.addObject("allPaintings", allPaintings);


        return mav;
    }

    @GetMapping("/logout")
    public String logOutUser(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }


}
