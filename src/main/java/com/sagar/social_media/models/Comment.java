package com.sagar.social_media.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private  String content;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<User> liked=new ArrayList<>();
    private LocalDateTime createdAt;

}
