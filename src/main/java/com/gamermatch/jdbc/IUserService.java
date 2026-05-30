package com.gamermatch.jdbc;

import com.gamermatch.common.PagedList;

public interface IUserService {
    User create(UserRequest request);
    User getById(Long id);
    PagedList<User> getAll();
}
