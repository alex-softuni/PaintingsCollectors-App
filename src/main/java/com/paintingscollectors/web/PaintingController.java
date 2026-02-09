package com.paintingscollectors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/paintings")
public class PaintingController {

    @GetMapping("/add-painting")
    public String addPainting() {
        return "new-painting";
    }
}
