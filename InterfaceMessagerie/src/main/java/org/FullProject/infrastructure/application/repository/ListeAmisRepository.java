package org.FullProject.infrastructure.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.FullProject.infrastructure.application.model.ListeAmis;

@Repository
public interface ListeAmisRepository extends CrudRepository<ListeAmis, Long> {
}
