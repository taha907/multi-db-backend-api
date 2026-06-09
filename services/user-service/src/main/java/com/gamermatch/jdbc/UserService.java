package com.gamermatch.jdbc;

import com.gamermatch.common.BadRequestException;
import com.gamermatch.common.PagedList;
import com.gamermatch.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Bu email zaten kayitli");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setGameRank(request.getGameRank());
        return userRepository.insert(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kullanici bulunamadi: " + id));
    }

    @Override
    public PagedList<User> getAll() {
        return new PagedList<>(userRepository.findAll());
    }
}
