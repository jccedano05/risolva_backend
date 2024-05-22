package com.jccv.risolva.model.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}