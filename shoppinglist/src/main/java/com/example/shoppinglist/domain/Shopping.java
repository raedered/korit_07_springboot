package com.example.shoppinglist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String product;
    private long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Shopping(String product, long amount, User user) {
        this.product = product;
        this.amount = amount;
        this.user = user;
    }
}
