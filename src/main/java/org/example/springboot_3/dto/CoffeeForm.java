package org.example.springboot_3.dto;


import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.springboot_3.entity.Coffee;

@ToString
@AllArgsConstructor
public class CoffeeForm {

    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
