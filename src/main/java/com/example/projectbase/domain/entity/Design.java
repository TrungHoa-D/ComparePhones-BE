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
@Table(name = "screens")
public class Design {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private String id;

    @Nationalized
    @Column
    private String size;

    @Nationalized
    @Column
    private String weight;

    @Nationalized
    @Column
    private String material;

    //Link to table Phone
    @ManyToOne
    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_DESIGN_PHONE"))
    private Phone phoneDesign;
}
