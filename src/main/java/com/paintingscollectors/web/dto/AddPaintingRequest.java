package com.paintingscollectors.web.dto;

import com.paintingscollectors.painting.model.Style;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPaintingRequest {

    @NotNull
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;
    @NotNull
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 40 characters!")
    private String author;

    @URL(message = "Please enter valid image URL!")
    private String URL;


    private Style style;

}
