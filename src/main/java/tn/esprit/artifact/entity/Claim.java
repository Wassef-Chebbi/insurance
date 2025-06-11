package tn.esprit.artifact.entity;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.artifact.enums.ClaimStatus;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private String description;

    private LocalDate declartionDate;

}
