package org.example.springboot_3.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.springboot_3.entity.Member;

@ToString
@AllArgsConstructor
public class MemberForm {
    private final String email;
    private final String password;

//    public MemberForm(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    @Override
//    public String toString() {
//        return "MemberForm{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
