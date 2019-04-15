package com.example.springboot_integration.service;

import com.example.springboot_integration.entity.Address;
import com.example.springboot_integration.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository ar;
    public Address addAddress(Address address){
        return ar.refresh(ar.save(address));
    }
    public List<Address> listAddresses(int uid){
        return ar.list(uid);
    }

    public Address updateAddress(Address address) {
        return Optional.ofNullable(ar.find(address.getUser().getId(), address.getId()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
                })
                .map(a -> ar.saveAndFlush(address))
                .get();
    }
}
