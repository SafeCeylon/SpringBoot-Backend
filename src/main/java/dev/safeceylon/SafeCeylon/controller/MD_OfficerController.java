package dev.safeceylon.SafeCeylon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/MD_Officer")

public class MD_OfficerController {


    public String saveMD_Officer(){
        String msg = "dd";
        return msg;
    }
}
