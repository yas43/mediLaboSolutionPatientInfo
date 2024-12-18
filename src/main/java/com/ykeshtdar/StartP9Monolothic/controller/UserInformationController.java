package com.ykeshtdar.StartP9Monolothic.controller;

import com.ykeshtdar.StartP9Monolothic.model.*;
import com.ykeshtdar.StartP9Monolothic.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("patient_info")
public class UserInformationController {
    private final UserInformationService userInformationService;

    public UserInformationController(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping("/name")
    public UserInformation displayUserByName(@RequestParam("firstname")String name){
        return userInformationService.findByUsername(name);
    }

    @GetMapping("/all")
    public List<UserInformation> displayAllUser(){
        return userInformationService.displayAllUserInformation();
    }

    @PostMapping("/add")
    public UserInformation addUser(@RequestBody UserInformation userInformation, HttpServletRequest request){

         return userInformationService.addUserInformation(userInformation);
    }

    @PostMapping("/update")
    public UserInformation update(@RequestParam("id")Integer id,
                                  @RequestParam("firstname")String firstname,
                                  @RequestParam("lastname")String lastname,
                                  @RequestParam("gender")String gender,
                                  @RequestParam("birthdate") LocalDate birthdate,
                                  @RequestParam("address")String address,
                                  @RequestParam("phoneNumber")String phoneNumber
                                  ){

        return userInformationService.updateUserInformation(id,firstname,lastname,gender,birthdate,address,phoneNumber);
    }



    @GetMapping("/findById/{id}")
    public UserInformation findPatientById(@PathVariable Integer id){
        return userInformationService.findById(id);
    }


}
