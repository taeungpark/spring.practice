package com.example.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role")
@NoArgsConstructor
@Setter
@Getter
//@ToString // It is best not to use it to express entity relationships.
public class Role {
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(length = 20)
    private String name;

    @Override
    public String toString() {
        return "Role {roleId= " + roleId + ", name= " + name + "}";
    }
}
