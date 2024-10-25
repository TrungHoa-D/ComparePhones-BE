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
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String ram;

    @Nationalized
    @Column
    private String internalMemory;

    @Nationalized
    @Column
    private String memoryCardSlot;

    //Link to table Phone
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storage")
    @JsonIgnore
    private Set<Phone> phones = new HashSet<>();
}
