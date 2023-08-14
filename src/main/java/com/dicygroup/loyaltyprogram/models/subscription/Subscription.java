package com.dicygroup.loyaltyprogram.models.subscription;

import com.dicygroup.loyaltyprogram.models.customer.Customer;
import com.dicygroup.loyaltyprogram.models.plans.AbstractPlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)//temp
    private Customer subscriber;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private AbstractPlan plan;

    //@Getter
    //@Setter
    //private String dateSubscription;

    @Getter
    @Setter
    private int points;

    public Subscription(Customer subscriber, AbstractPlan plan) {
        this.subscriber = subscriber;
        this.plan = plan;
        this.points = 0;
    }
}
