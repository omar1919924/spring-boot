// missing the functions

package com.covoiturage.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    protected long user_id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String password;
    protected String telephone;






    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(nom, user.nom) && Objects.equals(prenom, user.prenom) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(telephone, user.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, nom, prenom, email, password, telephone);
    }
}
