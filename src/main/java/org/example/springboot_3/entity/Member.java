package org.example.springboot_3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    private String password;


//    public Member(Long id, String email, String password) {
//        Id = id;
//        this.email = email;
//        this.password = password;
//    }

    public Member() {

    }

//    @Override
//    public String toString() {
//        return "Member{" +
//                "Id=" + Id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }
}
