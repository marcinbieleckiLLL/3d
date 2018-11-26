package com.greencrane.entity;

import com.greencrane.consts.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name="log__id"))
@AllArgsConstructor
public class Logs extends AbstractEntity {
    @Column(name = "ip")
    private String ip;
    @Column(name = "method")
    private String method;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "log_type")
    private LogType type;


    @Override
    public String toString() {
        return "Logs{" +
                "ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", type=" + type +
                '}';
    }
}
