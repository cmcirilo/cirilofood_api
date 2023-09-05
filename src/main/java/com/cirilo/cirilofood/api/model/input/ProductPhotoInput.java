package com.cirilo.cirilofood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.cirilo.cirilofood.core.validation.FileContentType;
import com.cirilo.cirilofood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoInput {

    @ApiModelProperty(hidden = true)
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @ApiModelProperty(value = "Description product photo")
    @NotBlank
    private String description;

}
