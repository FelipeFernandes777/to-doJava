package br.com.to_do.controller;

import br.com.to_do.domain.user.CreateUserDTO;
import br.com.to_do.domain.user.UpdateUserDTO;
import br.com.to_do.domain.user.User;
import br.com.to_do.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findOneById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@RequestParam String id, @RequestBody UpdateUserDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.updateAUser(id, data));
    }

    @DeleteMapping("/disable/{id}")
    public void disableUser(@RequestParam String id) {
        this.service.disableUserById(id);
        ResponseEntity.status(HttpStatus.OK).body("User disable with success!");
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody CreateUserDTO data, UriComponentsBuilder uriComponentsBuilder) {
        var user = this.service.createAUser(data);

        URI uri = uriComponentsBuilder.path("/user/create/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }
}
