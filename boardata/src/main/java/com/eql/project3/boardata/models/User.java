package com.eql.project3.boardata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String firstname;

    private String email;

    private String address;

    private String phoneNumber;

    private String password;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registerDate;

    @OneToMany(mappedBy = "requester"
    )
    private List<Friendship> friendRequests  = new ArrayList<>();

    @OneToMany(mappedBy = "friend"
    )
    private List<Friendship> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Result> results = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

    public User(String name, String firstname, String email, String address, String phoneNumber, String password, Date registerDate, List<Role> roles) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.registerDate = registerDate;
        this.roles = roles;
    }
}
