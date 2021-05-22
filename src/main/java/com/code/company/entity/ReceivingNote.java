package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ReceivingNote{
    @Id
    @SequenceGenerator(
            name = "recv_note_generator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recv_note_generator"
    )
    private Long id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name="id", nullable=false)
    private OrderMain order;

}
