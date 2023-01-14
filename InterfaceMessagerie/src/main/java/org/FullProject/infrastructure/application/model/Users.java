package org.FullProject.infrastructure.application.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.List;

@Data
@Table(name = "Users")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "mail")
    private String mail;
    @Column(name = "password")
    private String password;
    @Column(name = "banni")
    private Boolean banni;
    @Column(name = "date_Naissance")
    private String date_Naissance;
    @Column(name = "ip")
    private String ip;
    @Column(name = "port")
    private Short port;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private List<ListeAmis> listeAmis;
   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "users2")
    private List<ListeAmis> listeAmis2;*/
}
