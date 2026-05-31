package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.dto.OnCreate;
import org.example.dto.OnUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 2, max = 100, groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Column(unique = true)  // добавьте в БД
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 255, groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 150, groups = {OnCreate.class, OnUpdate.class})
    private Integer age;
    private LocalDateTime created_at;

    public User() {
    }

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.created_at = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setAge(Integer age) { this.age = age; }

    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public Integer getAge() { return age; }

    public LocalDateTime getCreated_at() { return created_at; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", created_at=" + created_at +
                '}';
    }
}
