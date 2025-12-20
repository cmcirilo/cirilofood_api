package com.cirilo.cirilofood.core.web;

import org.springframework.http.MediaType;

public class CiriloMediaTypes {

    public static final String V1_APPLICATION_JSON_VALUE = "application/vnd.cirilofood.v1+json";

    public static final MediaType V1_APPLICATION_JSON = MediaType.valueOf(V1_APPLICATION_JSON_VALUE);

}
