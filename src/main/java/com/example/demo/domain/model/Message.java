package com.example.demo.domain.model;

import com.example.demo.domain.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    @NotNull
    private Category category;

    @NotBlank
    private String content;

}
