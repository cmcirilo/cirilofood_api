package com.cirilo.cirilofood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cirilo.cirilofood.api.v1.model.AddressModel;
import com.cirilo.cirilofood.api.v1.model.input.OrderItemInput;
import com.cirilo.cirilofood.api.v2.model.input.CityInputV2;
import com.cirilo.cirilofood.domain.model.Address;
import com.cirilo.cirilofood.domain.model.City;
import com.cirilo.cirilofood.domain.model.OrderItem;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CityInputV2.class, City.class)
                .addMappings(mapper -> mapper.skip(City::setId));

        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        var addressToAddressModelTypeMap =
                modelMapper.createTypeMap(Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String> addMapping(
                addressSource -> addressSource.getCity().getState().getName(),
                (targetAddressModel, value) -> targetAddressModel.getCity().setState(value));

        return modelMapper;
    }
}
