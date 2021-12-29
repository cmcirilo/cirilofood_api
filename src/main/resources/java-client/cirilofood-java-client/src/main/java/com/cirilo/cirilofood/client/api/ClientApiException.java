package com.cirilo.cirilofood.client.api;

import com.cirilo.cirilofood.client.model.Problem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
public class ClientApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private Problem problem;

    public ClientApiException(String message, RestClientResponseException cause) {
        super(message, cause);

        deserializaProblem(cause);
    }

    private void deserializaProblem(RestClientResponseException cause) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();

        try {
            this.problem = objectMapper.readValue(cause.getResponseBodyAsString(), Problem.class);
        } catch (JsonProcessingException e) {
            log.warn("Its not possible deserialize response into a problem", e);
        }
    }
}
