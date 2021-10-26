package com.cirilo.cirilofood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cirilo.cirilofood.core.validation.FileSize;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoInput {

    @NotNull
    @FileSize(max = "500KB")
    private MultipartFile file;

    @NotBlank
    private String description;

}
