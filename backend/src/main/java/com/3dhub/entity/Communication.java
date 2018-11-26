package com.greencrane.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Marcin on 30.09.2018.
 */
@Entity
@Table(name = "communication")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name="communication_id"))
public class Communication extends AbstractEntity{

    @Column(name = "sms_sended")
    public Boolean smsSended;
    @Column(name = "mail_sended")
    public Boolean mailSended;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    public File file;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    public Customer customer;

    public static Communication getDefault() {
        Communication communication = new Communication();
        communication.setMailSended(Boolean.FALSE);
        communication.setSmsSended(Boolean.FALSE);
        return communication;
    }
}
