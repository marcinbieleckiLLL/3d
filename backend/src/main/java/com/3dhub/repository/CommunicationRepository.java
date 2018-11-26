package com.greencrane.repository;

import com.greencrane.entity.Communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcin on 30.09.2018.
 */
@RepositoryRestResource
public interface CommunicationRepository extends JpaRepository<Communication, Integer> {
    Communication save(Communication communication);
}
