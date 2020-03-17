package com.zay.depensesbe.controllers;


import com.zay.depensesbe.data.User;
import com.zay.depensesbe.dto.requests.LoginRequest;
import com.zay.depensesbe.services.UsersService;
import com.zay.depensesbe.utlis.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(Constants.BASE_URI + "/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UsersService usersServices;

    @GetMapping("/all")
    public Collection<User> getAllusers(){
        return this.usersServices.findAll();
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody  LoginRequest loginRequest){
        return this.usersServices.login(loginRequest.getLogin(), loginRequest.getPassword());
    }
}
