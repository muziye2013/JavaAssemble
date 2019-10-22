package com.tison.basic.web;

import com.tison.basic.config.excutor.ExcutorConfiguration;
import com.tison.basic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tison
 * @date 2019/10/22
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ExcutorConfiguration excutorConfiguration;

    @GetMapping("/getAll")
    public String getAll(){
        return excutorConfiguration.excutorProperties().toString();
    }
}
