package com.greencrane.repository;

import com.greencrane.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Marcin on 29.09.2018.
 */
@RepositoryRestResource
public interface FileRepository extends JpaRepository<File, Integer> {
    File save(File file);
}
