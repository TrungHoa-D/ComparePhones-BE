package com.example.projectbase.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_STORAGE_PHONE"))
    private Phone phoneStorage;
}
