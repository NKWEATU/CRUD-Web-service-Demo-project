package net.javaguides.springboot_restful_webservices_kennedy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot_restful_webservices_kennedy.dto.UserDto;
import net.javaguides.springboot_restful_webservices_kennedy.entity.User;
import net.javaguides.springboot_restful_webservices_kennedy.exception.ErrorDetails;
import net.javaguides.springboot_restful_webservices_kennedy.exception.ResourceNotFoundException;
import net.javaguides.springboot_restful_webservices_kennedy.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(

        name ="CRUD REST APIs for users resource",
        description =  "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController//this annotation is a combination of both @Controller and @ResponseBody
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "CREATE USER REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP 202 CREATED"
    )
    //build create User REST API
    @PostMapping//this annotation is used to map HTTP POST request onto specific handler method
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
       UserDto savedUser = userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET USER BY ID REST API",
            description = "Get  User by ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200 SUCCESS"
    )
    //build get user by id REST API
    //the request link will be http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "GET ALL USERS REST API",
            description = "GET ALL USERs REST API is used to get all users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200 SUCCESS"
    )
    //build Get All Users REST API
    //the request link will be http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @Operation(
            summary = "UPDATE USER REST API",
            description = "UPDATE USER REST API is used to update a user info in  the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200 SUCCESS"
    )
    //Build Update User REST API
    //the request link will be http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @Operation(
            summary = "DELETE USER REST API",
            description = "DELETE USER REST API is used to delete a user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP 200 SUCCESS"
    )
    //Build Delete User REST API
    //the request link will be http://localhost:8080/api/users/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);

    }

    //@ExceptionHandler annotation for specific exceptions
//    //and return the custom response to the client
//@ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
