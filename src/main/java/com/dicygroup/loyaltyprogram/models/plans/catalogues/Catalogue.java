package com.dicygroup.loyaltyprogram.models.plans.catalogues;

import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import com.dicygroup.loyaltyprogram.models.plans.Plan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prize> prizes;

    @OneToOne
    @Getter
    @Setter
    private AbstractPlan plan;

    public void addPrize(Prize prize) {
        this.prizes.add(prize);
    }

    public void removePrize(Prize prize) {
        this.prizes.remove(prize);
    }
}
