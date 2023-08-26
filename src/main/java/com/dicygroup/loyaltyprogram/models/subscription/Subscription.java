package com.dicygroup.loyaltyprogram.models.subscription;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)//temp
    private Customer subscriber;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private AbstractPlan plan;

    @Getter
    @Setter
    private int points;

    public Subscription(Customer subscriber, AbstractPlan plan) {
        this.subscriber = subscriber;
        this.plan = plan;
        this.points = 0;
    }
}
