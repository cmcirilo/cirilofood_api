package com.cirilo.cirilofood.client.model;

import lombok.Data;

@Data
public class AddressModel {

    private String zipCode;

    private String street;

    private String number;

    private String complement;

    private String district;

    private CityResumeModel city;

}
