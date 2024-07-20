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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String caption;
    private String image;
    private String video;
    @ManyToOne
    private User user;
//    private Integer userId;
    private LocalDateTime createdAt;
    @ElementCollection
    private List<Integer> liked=new ArrayList<>();
    @OneToMany
    private List<Comment>comments=new ArrayList<>();

}
