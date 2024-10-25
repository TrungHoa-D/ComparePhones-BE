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
    @ManyToOne
    @JoinColumn(name = "phone_id", foreignKey = @ForeignKey(name = "FK_CONNECTION_PHONE"))
    private Phone phoneConnection;
}
