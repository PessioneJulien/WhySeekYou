package org.FullProject.infrastructure.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.FullProject.infrastructure.application.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
}
