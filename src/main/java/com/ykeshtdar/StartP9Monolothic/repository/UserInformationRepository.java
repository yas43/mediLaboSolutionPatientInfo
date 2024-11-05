package com.ykeshtdar.StartP9Monolothic.repository;

import com.ykeshtdar.StartP9Monolothic.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface UserInformationRepository extends JpaRepository<UserInformation,Integer> {

   Optional<UserInformation> findByFirstname(String name);
   Boolean existsByFirstname(String firstname);
}
