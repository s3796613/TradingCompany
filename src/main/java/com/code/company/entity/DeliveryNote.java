package com.code.company.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DeliveryNote {
    @Id
    @SequenceGenerator(
            name = "deli_note_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deli_note_sequence"
    )
    private Long id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Staff staff;
    @ElementCollection
    private List<PackageDetail> deliveryDetails;

}
