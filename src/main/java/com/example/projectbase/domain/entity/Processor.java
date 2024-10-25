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
@Table(name = "processors")
public class Processor {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String chipset;

    @Nationalized
    @Column
    private String cpu;

    @Nationalized
    @Column
    private String gpu;

    //Link to table Phone
    @ManyToOne
    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_PROCESSOR_PHONE"))
    private Phone phonePro;

}
