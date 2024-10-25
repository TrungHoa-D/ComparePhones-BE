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
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column(nullable = false)
    private String name;

    @Nationalized
    @Column(nullable = false)
    private String brand;

    @Nationalized
    @Column
    private String releaseDate;

    @Nationalized
    @Column(nullable = false)
    private String cost;

    @Nationalized
    @Column
    private String img;

    @Nationalized
    @Column
    private String color;

    //Link to table Screen
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneScreen")
    @JsonIgnore
    private Set<Screen> screens = new HashSet<>();

    //Link to table Camera
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneCamera")
    @JsonIgnore
    private Set<Camera> cameras = new HashSet<>();

    //Link to table Processor
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phonePro")
    @JsonIgnore
    private Set<Processor> processors = new HashSet<>();

    //Link to table Connection
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneConnection")
    @JsonIgnore
    private Set<Connection> connections = new HashSet<>();

    //Link to table Storage
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneStorage")
    @JsonIgnore
    private Set<Storage> storages = new HashSet<>();

    //Link to table Battery
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneBattery")
    @JsonIgnore
    private Set<Battery> batteries = new HashSet<>();

    //Link to table Design
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneDesign")
    @JsonIgnore
    private Set<Design> designs = new HashSet<>();

    //Link to table Other
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phoneOther")
    @JsonIgnore
    private Set<OtherInfor> otherInfors = new HashSet<>();
}
