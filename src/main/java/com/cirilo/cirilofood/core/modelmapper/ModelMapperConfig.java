package com.cirilo.cirilofood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cirilo.cirilofood.api.model.AddressModel;
import com.cirilo.cirilofood.domain.model.Address;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        var addressToAddressModelTypeMap =
                modelMapper.createTypeMap(Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String> addMapping(
                addressSource -> addressSource.getCity().getState().getName(),
                (targetAddressModel, value) -> targetAddressModel.getCity().setState(value));

        return modelMapper;
    }
}
