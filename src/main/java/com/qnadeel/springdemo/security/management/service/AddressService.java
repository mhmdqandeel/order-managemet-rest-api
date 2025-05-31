package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.request.AddressRequest;
import com.qnadeel.springdemo.security.management.entities.Address;
import com.qnadeel.springdemo.security.management.entities.User;
import com.qnadeel.springdemo.security.management.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;

    private final UserService userService;

    public Address saveAddress(Long userId, AddressRequest address) {

        User user = userService.getUserByID(userId);

        Address newAddress = Address.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .zip(address.getZip())
                .build();

        user.addAddress(newAddress);

        return addressRepository.save(newAddress);
    }

    public List<Address> getUserAddresses(Long userId) {
        User user = userService.getUserByID(userId);

        List<Address> addresses = user.getAddresses();

        if (addresses == null || addresses.isEmpty()) {
            throw new RuntimeException("No addresses found for user with ID: " + userId);
        }

        return addresses;
    }

    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("No address found with ID: " + addressId));
    }

    public Address updateAddress(Long addressId, AddressRequest updatedAddress) {

        Address address = getAddressById(addressId);

        address.setStreet(updatedAddress.getStreet());
        address.setCity(updatedAddress.getCity());
        address.setZip(updatedAddress.getZip());

        return addressRepository.save(address);
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
