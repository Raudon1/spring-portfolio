package com.cac.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String direction;
    private String phoneNumber;
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<CustomerOrder> customerOrders = new ArrayList<>();

}