package com.gamermatch.jdbc;

import com.gamermatch.common.ApiResponse;
import com.gamermatch.common.PagedList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<User> create(@Valid @RequestBody UserRequest request) {
        return ApiResponse.ok(userService.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getById(@PathVariable Long id) {
        return ApiResponse.ok(userService.getById(id));
    }

    @GetMapping
    public ApiResponse<PagedList<User>> getAll() {
        return ApiResponse.ok(userService.getAll());
    }
}
