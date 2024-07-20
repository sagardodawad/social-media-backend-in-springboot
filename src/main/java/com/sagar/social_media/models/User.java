package com.sagar.social_media.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    @ElementCollection
    private List<Integer> followings;

    @ElementCollection
    private List<Integer> followers;
    @ElementCollection
    private List<Integer> savedPost=new ArrayList<>();
}
