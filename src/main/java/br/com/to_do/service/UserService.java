package br.com.to_do.service;

import br.com.to_do.domain.user.UpdateUserDTO;
import br.com.to_do.domain.user.CreateUserDTO;
import br.com.to_do.domain.user.User;
import br.com.to_do.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private final User user = new User();

    public List<User> findAllUsers() {
        return this.repository.findAll();
    }

    public User findOneById(String id) {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public User createAUser(CreateUserDTO data) {
        var newUser = this.user.create(data);
        this.repository.save(newUser);
        return newUser;
    }

    public User updateAUser(String id, UpdateUserDTO data) {
        var updatedUser = this.repository.findById(id).orElseThrow(RuntimeException::new);
        updatedUser.update(data);
        this.repository.save(updatedUser);
        return updatedUser;
    }

    public void disableUserById(String id) {
        var user = this.repository.findById(id).orElseThrow(RuntimeException::new);
        user.disableUser();
    }
}
