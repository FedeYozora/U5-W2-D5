package it.epicode.Device.Management.controllers;

import it.epicode.Device.Management.entities.User;
import it.epicode.Device.Management.payloads.NewUserDTO;
import it.epicode.Device.Management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return userService.getUsers(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public User findByID(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveNewUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException();
        } else {
            return userService.save(body);
        }
    }

    @PutMapping("/{id}")
    public User findByIDAndUpdate(@PathVariable UUID id, @RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new RuntimeException();
        } else {
            return userService.findByIDAndUpdate(id, body);
        }
    }

    @DeleteMapping("/{id}")
    public void findByIDAndDelete(@PathVariable UUID id) {
        userService.findByIDAndDelete(id);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("avatar") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return userService.uploadImg(body);
    }
}
