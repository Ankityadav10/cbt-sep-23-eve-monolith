package com.cbteve.cbtsep23evemonolith.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usertypelinks")
public class Usertypelink {
    @Id
    @Column(name = "linkid", nullable = false, length = 10)
    private String linkid;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "type", length = 10)
    private String type;

}