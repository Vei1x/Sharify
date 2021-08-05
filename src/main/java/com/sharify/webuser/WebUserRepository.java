package com.sharify.user;


import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository {
    Optional<WebUser> findByEmail (String email);
}
