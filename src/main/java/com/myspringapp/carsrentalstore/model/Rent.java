package com.myspringapp.carsrentalstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "rents")
@Data
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car carId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_finished")
    private boolean isFinished;

    @JsonBackReference
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
}
