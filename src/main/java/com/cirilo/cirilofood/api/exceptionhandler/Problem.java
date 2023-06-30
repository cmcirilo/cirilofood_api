package com.cirilo.cirilofood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problem")
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private final Integer status;

    @ApiModelProperty(example = "2023-12-01T18:00:02.70044Z", position = 10)
    private final String type;

    @ApiModelProperty(example = "https://cirilofood.com.br/invalid-data", position = 20)
    private final String title;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 30)
    private final String detail;

    @ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", position = 40)
    private final String userMessage;

    private final OffsetDateTime timestamp;

    @ApiModelProperty(value = "Object List or Fields that made error (opcional)", position = 50)
    private final List<Object> objects;

    @ApiModel("Object Problem")
    @Getter
    @Builder
    public static class Object {

        @ApiModelProperty(example = "name", position = 1)
        private final String name;

        @ApiModelProperty(example = "City Name is mandatory")
        private final String userMessage;

    }
}
