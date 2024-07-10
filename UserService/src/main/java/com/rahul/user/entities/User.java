package com.rahul.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {
    @Id
    @Column(name = "U_ID")
    private String userId;

    @Column(name ="U_NAME")
    private String username;

    @Column(name = "EMAILID")
    private String emailId;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
