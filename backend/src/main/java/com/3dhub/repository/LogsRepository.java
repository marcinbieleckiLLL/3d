package com.greencrane.repository;

import com.greencrane.entity.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LogsRepository extends JpaRepository<Logs, Integer> {
    Logs save(Logs logs);
}
