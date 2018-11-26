package com.greencrane.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntity extends AbstractPersistable<Integer>  {

    @Column(name = "mod_date")
    @JsonIgnore
    private Date modDate;
}
