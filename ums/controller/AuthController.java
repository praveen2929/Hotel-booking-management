//package com.ums.controller;
//
//import com.ums.entity.AppUser;
//import com.ums.payload.LoginDto;
//import com.ums.payload.UserDto;
//import com.ums.service.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//    private UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/{addUser}")
//    public ResponseEntity<UserDto>addUser(@RequestBody UserDto userDto){
//        UserDto dto = userService.addUser(userDto);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
//        String token = userService.verifyLogin(loginDto);
//        if(token !=null){
//            return new ResponseEntity<>(token,HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Invalid Credential",HttpStatus.UNAUTHORIZED);
//
//    }
//    @GetMapping("/profile")
//    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser user)
//    {
//        return user;
//    }
//}


package com.ums.controller;

import com.ums.config.JWTResponseFilter;
import com.ums.entity.AppUser;
import com.ums.payload.JwtResponse;
import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;
import com.ums.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{addUser}")
    public ResponseEntity<?>addUser(
            @Valid @RequestBody UserDto userDto,
            BindingResult bindingResult
    ){

        //name , email, mobile number validation
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //System.out.println(userDto.getUsername());
        UserDto dto=userService.addUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDto loginDto){
//        System.out.println(loginDto.getUsername());
//        System.out.println(loginDto.getPassword());

//          boolean status=userService.verifyLogin(loginDto);
        String token=userService.verifyLogin(loginDto);

//          return new ResponseEntity<>("Done",HttpStatus.OK);
//        if(status){
//            return new ResponseEntity<>("User Signed in",HttpStatus.OK);
//        }
        if(token!=null){
            JwtResponse response=new JwtResponse();
            response.setToken(token);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);

    }
    @GetMapping("/profile")
    public AppUser getCurrentProfile(@AuthenticationPrincipal AppUser user){
        return  user;
    }
}


