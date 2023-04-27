package com.kh.learnovation.domain.admin.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name="admins")
public class Admin {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="pwd")
    private String pwd;
    @Column(name="email")
    private String email;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(name="update_at")
    private Timestamp updateAt;

    @Builder
    public Admin(long id, String name, String pwd, String email, Timestamp createdAt, Timestamp updateAt){
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
