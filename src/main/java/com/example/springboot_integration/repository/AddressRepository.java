package com.example.springboot_integration.repository;

import com.example.springboot_integration.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CustomizedRepository <Address,Integer> {
//    List<Address> findByUser(User user);
    @Query("select a from Address a where a.user.id=:uid")
    List<Address> list(@Param("uid")int uid);
    @Query("select a from Address a where a.user.id=:uid and a.id=:aid")
    Address find(@Param("uid")int uid,@Param("aid")int aid);

}
