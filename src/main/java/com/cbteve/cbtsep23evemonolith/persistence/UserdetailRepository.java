package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserdetailRepository extends JpaRepository<Userdetail, String> {
    @Transactional
    @Modifying
    @Query("update Userdetail u set u.fname = ?1, u.lname = ?2, u.email = ?3, u.phone = ?4, u.city = ?5, u.country = ?6 " +
            "where u.username = ?7")
    int updateFnameAndLnameAndEmailAndPhoneAndCityAndCountryByUsername(String fname, String lname, String email, String phone, String city, String country, String username);



}