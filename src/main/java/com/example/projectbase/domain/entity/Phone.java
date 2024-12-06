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
    private int cost;

    @Nationalized
    @Column
    private String img;

    @Nationalized
    @Column
    private String color;

    //Link to table Screen
    @ManyToOne
    @JoinColumn(name = "screen_id", foreignKey = @ForeignKey(name = "FK_PHONE_SCREEN"))
    private Screen screen;

    //Link to table Camera
    @ManyToOne
    @JoinColumn(name = "camera_id", foreignKey = @ForeignKey(name = "FK_PHONE_CAMERA"))
    private Camera camera;

    //Link to table Processor
    @ManyToOne
    @JoinColumn(name = "processor_id", foreignKey = @ForeignKey(name = "FK_PHONE_PROCESSOR"))
    private Processor processor;

    //Link to table Connection
    @ManyToOne
    @JoinColumn(name = "connection_id", foreignKey = @ForeignKey(name = "FK_PHONE_CONNECTION"))
    private Connection connection;

    //Link to table Storage
    @ManyToOne
    @JoinColumn(name = "storage_id", foreignKey = @ForeignKey(name = "FK_PHONE_STORAGE"))
    private Storage storage;

    //Link to table Battery
    @ManyToOne
    @JoinColumn(name = "battery_id", foreignKey = @ForeignKey(name = "FK_PHONE_BATTERY"))
    private Battery battery;

    //Link to table Design
    @ManyToOne
    @JoinColumn(name = "design_id", foreignKey = @ForeignKey(name = "FK_PHONE_DESIGN"))
    private Design design;

    //Link to table Other
    @ManyToOne
    @JoinColumn(name = "otherInfor_id", foreignKey = @ForeignKey(name = "FK_PHONE_OTHERINFOR"))
    private OtherInfor otherInfor;

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", cost='" + cost + '\'' +
                ", img='" + img + '\'' +
                ", color='" + color + '\'' +
                ", screen=" + screen +
                ", camera=" + camera +
                ", processor=" + processor +
                ", connection=" + connection +
                ", storage=" + storage +
                ", battery=" + battery +
                ", design=" + design +
                ", otherInfor=" + otherInfor +
                '}';
    }

}
