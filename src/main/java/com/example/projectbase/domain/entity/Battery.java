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
@Table(name = "batteries")
public class Battery {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String battery;

    @Nationalized
    @Column
    private String charginTechnology;

    @Nationalized
    @Column
    private String port;

    //Link to table Phone
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "battery")
    @JsonIgnore
    private Set<Phone> phones = new HashSet<>();
}
