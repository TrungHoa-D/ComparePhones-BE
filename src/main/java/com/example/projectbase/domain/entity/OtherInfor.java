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
@Table(name = "otherInfors")
public class OtherInfor {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String cooler;

    @Nationalized
    @Column
    private String resistanceIndex;

    @Nationalized
    @Column
    private String tech;

    @Nationalized
    @Column
    private String soundTech;

    @Nationalized
    @Column
    private String utilities;

    @Nationalized
    @Column(columnDefinition = "LONGTEXT")
    private String sensor;

    //Link to table Phone
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otherInfor")
    @JsonIgnore
    private Set<Phone> phones = new HashSet<>();
}
