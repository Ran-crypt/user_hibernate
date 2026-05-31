package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.groups.Default;

import java.time.LocalDateTime;

public class UserRequest {
    @NotBlank(message = "Имя не может быть пустым", groups = OnCreate.class)
    private String name;
    @NotBlank(message = "Email не может быть пустым", groups = OnCreate.class)
    @Email(message = "Некорректный email", groups = OnCreate.class)
    private String email;
    @Min(value = 0, message = "Возраст должен быть >= 0",
            groups = {Default.class, OnCreate.class, OnUpdate.class}
    )
    @Max(value = 150, message = "Возраст должен быть <= 150",
            groups = {Default.class, OnCreate.class, OnUpdate.class}
    )
    private Integer age;
    private LocalDateTime created_at;


    public UserRequest() {
    }

    public UserRequest(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = LocalDateTime.now();
    }

//    public Long getId(){ return id; };
//
//    public void setId(Long id){this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}