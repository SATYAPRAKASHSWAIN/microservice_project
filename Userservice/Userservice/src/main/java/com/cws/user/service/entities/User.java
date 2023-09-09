package com.cws.user.service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="micro_users")
public class User {

    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="NAME",length = 20)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private  String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();

}
