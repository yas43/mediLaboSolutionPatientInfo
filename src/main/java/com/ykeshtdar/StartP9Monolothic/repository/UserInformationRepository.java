package com.ykeshtdar.StartP9Monolothic.repository;

import com.ykeshtdar.StartP9Monolothic.model.*;
import org.springframework.data.jpa.repository.*;

import java.time.*;
import java.util.*;


public interface UserInformationRepository extends JpaRepository<UserInformation,Integer> {

   Optional<UserInformation> findByFirstname(String name);
   Boolean existsByFirstnameAndLastnameAndBirthdateAndGenderAndAddressAndPhoneNumber(String firstname,
                                                                                     String lastname,
                                                                                     LocalDate birthdate,
                                                                                     String gender,
                                                                                     String address,
                                                                                     String phoneNumber);
}
