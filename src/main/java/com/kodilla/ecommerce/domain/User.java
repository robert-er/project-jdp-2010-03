package com.kodilla.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname", "email"})})
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    @NotEmpty
    private String email;
    private boolean isBlocked;
    private String randomKey;
    private LocalDateTime timeOfCreationRandomKey;
    private LocalDateTime signUpDate;

    @JsonManagedReference(value = "user-order")
    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @Builder
    public User(String nickname, String name, String surname, String email, boolean isBlocked) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isBlocked = isBlocked;
        this.signUpDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                surname.equals(user.surname) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email);
    }
}