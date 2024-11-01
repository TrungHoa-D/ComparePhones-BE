package com.example.projectbase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String mobile_nfc;

    @Nationalized
    @Column
    private String sim;

    @Nationalized
    @Column
    private String os;

    @Nationalized
    @Column
    private String network;

    @Nationalized
    @Column
    private String wlan;

    @Nationalized
    @Column
    private String bluetooth;

    @Nationalized
    @Column
    private String gps;

    //Link to table Phone
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "connection")
    @JsonIgnore
    private Set<Phone> phones = new HashSet<>();
}