package com.cirilo.cirilofood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cirilo.cirilofood.api.v1.model.AddressModel;
import com.cirilo.cirilofood.api.v1.model.input.OrderItemInput;
import com.cirilo.cirilofood.domain.model.Address;
import com.cirilo.cirilofood.domain.model.OrderItem;

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

        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }
}
