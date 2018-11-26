package com.greencrane.service._abstract;


import com.greencrane.service._generic.RepositoryFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public abstract class AbstractServiceImpl<T extends AbstractPersistable> implements AbstractService<T>{

    @Autowired
    RepositoryFactoryService repositoryFactoryService;

    public T update(T t){
        JpaRepository repository = repositoryFactoryService.getRepository(t);
        if(repository != null) {
            T toSave = (T) repository.getOne(t.getId());
            Arrays.stream(toSave.getClass().getDeclaredFields()).forEach(f -> {
                try {
                    f.set(toSave, f.get(t));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return (T) repository.save(toSave);
        }
        return null;
    }
}
