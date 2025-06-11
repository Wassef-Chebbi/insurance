package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import tn.esprit.artifact.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ContractStatus statut;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @ManyToOne
    @JsonBackReference("offre_contrat_reference")
    Offer offer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("contrat_user_reference")
    private User user;



}
