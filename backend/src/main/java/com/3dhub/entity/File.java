package com.greencrane.entity;

import com.greencrane.consts.FileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Marcin on 29.09.2018.
 */
@Entity
@Table(name = "file")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name="file_id"))
public class File extends AbstractEntity{

    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "file_type")
    private FileType fileType;
    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private Communication communication;

    public void setCustomer(Customer customer) {
        if (this.communication == null) this.communication = Communication.getDefault();
        this.communication.setCustomer(customer);
    }
    public Customer getCustomer() {
        if (this.communication == null) return null;
        return this.communication.getCustomer();
    }
}
