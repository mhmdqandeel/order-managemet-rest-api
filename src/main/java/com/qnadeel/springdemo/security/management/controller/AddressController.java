package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.request.AddressRequest;
import com.qnadeel.springdemo.security.management.dto.response.AddressResponse;
import com.qnadeel.springdemo.security.management.mapper.AddressMapper;
import com.qnadeel.springdemo.security.management.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    @PostMapping("/{userId}")
    public ResponseEntity<AddressResponse> addAddress(@PathVariable Long userId,
                                                      @RequestBody AddressRequest address) {

        return ResponseEntity.ok(addressMapper
                .addressToAddressResponse(addressService
                        .saveAddress(userId, address)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AddressResponse>> getUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(
                addressMapper
                        .addressesToAddressesResponse(
                                addressService.getUserAddresses(userId)
                        )
        );
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(
                addressMapper
                        .addressToAddressResponse(
                                addressService
                                        .getAddressById(addressId)
                        )
        );
    }

    @PutMapping("/edit-address/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long addressId
                                                        ,@RequestBody AddressRequest updatedAddress) {

        return ResponseEntity.ok(addressMapper
                .addressToAddressResponse(addressService
                        .updateAddress(addressId, updatedAddress)));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Deleted address with id " + addressId);
    }
}
