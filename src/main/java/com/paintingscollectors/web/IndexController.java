package com.paintingscollectors.web;

import com.paintingscollectors.user.service.UserService;
import com.paintingscollectors.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView registerUser(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/register");
        }

        userService.registerUser(registerRequest);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }
}
