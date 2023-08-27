package com.dicygroup.loyaltyprogram.models.plans.catalogues;

import com.dicygroup.loyaltyprogram.models.plans.catalogues.costs.Cost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@NoArgsConstructor
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;

    @Getter
    @Setter
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "cost_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cost cost;

    public Prize(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }
}
