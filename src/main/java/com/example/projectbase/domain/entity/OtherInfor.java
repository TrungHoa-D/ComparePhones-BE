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
    @Column
    private String sensor;

    //Link to table Phone
    @ManyToOne
    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_OTHERINFOR_PHONE"))
    private Phone phoneOther;
}
