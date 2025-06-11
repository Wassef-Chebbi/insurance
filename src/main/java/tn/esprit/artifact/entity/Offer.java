package tn.esprit.artifact.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import tn.esprit.artifact.enums.OfferType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    private Double prix;

    private Long dureeMois;


    @OneToMany( cascade = CascadeType.ALL,mappedBy = "offer")
    @JsonManagedReference("offre_contrat_reference")
    @JsonIgnore
    private Set<Contract> contracts = new HashSet<>();




}
