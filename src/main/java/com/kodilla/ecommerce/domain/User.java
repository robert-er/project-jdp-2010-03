package com.kodilla.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String name;
    private String surname;
    private String email;
    private boolean isBlocked;
    private String randomKey;
    private LocalDateTime timeOfCreationRandomKey;
    private LocalDateTime signUpDate;

    @JsonManagedReference(value = "user-order")
    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Order> orders;

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