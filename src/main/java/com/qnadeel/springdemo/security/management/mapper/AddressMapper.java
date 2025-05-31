package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.response.AddressResponse;
import com.qnadeel.springdemo.security.management.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Builder
@Data
public class AddressMapper {

    public AddressResponse addressToAddressResponse(Address address){
        return AddressResponse.builder()
                .userId(address.getUser().getUserId())
                .city(address.getCity())
                .street(address.getStreet())
                .zip(address.getZip())
                .build();
    }

    public List<AddressResponse> addressesToAddressesResponse(List<Address> addresses){
        return addresses.stream()
                .map(address -> AddressResponse.builder()
                        .userId(address.getUser().getUserId())
                        .city(address.getCity())
                        .street(address.getStreet())
                        .zip(address.getZip())
                        .build())
                .toList();
    }

}
