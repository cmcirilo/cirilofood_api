package com.cirilo.cirilofood.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content", page.getContent());
        jsonGenerator.writeObjectField("pageSize", page.getSize());
        jsonGenerator.writeObjectField("totalElements", page.getTotalElements());
        jsonGenerator.writeObjectField("totalPages", page.getTotalPages());
        jsonGenerator.writeObjectField("number", page.getNumber());

        jsonGenerator.writeEndObject();
    }
}
