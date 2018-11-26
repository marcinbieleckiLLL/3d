package com.greencrane.service._generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

public interface RepositoryFactoryService {
    JpaRepository getRepository(AbstractPersistable entity);
}

@Service
class RepositoryFactoryServiceImpl implements RepositoryFactoryService{

    private Repositories repositories = null;

    @Autowired
    public RepositoryFactoryServiceImpl(WebApplicationContext appContext) {
        this.repositories = new Repositories(appContext);
    }

    @Override
    public JpaRepository getRepository(AbstractPersistable entity) {
        Object repo =  repositories.getRepositoryFor(entity.getClass());
        if(repo instanceof JpaRepository) return (JpaRepository) repo;
        return null;
    }
}