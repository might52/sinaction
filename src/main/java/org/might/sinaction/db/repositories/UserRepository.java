package org.might.sinaction.db.repositories;

import org.might.sinaction.db.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
