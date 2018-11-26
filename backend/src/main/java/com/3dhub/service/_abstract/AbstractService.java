package com.greencrane.service._abstract;

import org.springframework.data.jpa.domain.AbstractPersistable;

public interface AbstractService<T extends AbstractPersistable> {
    T update(T t);
}
