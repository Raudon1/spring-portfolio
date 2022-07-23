package com.cac.portfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String city;
    private String direction;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private Collection<Client> clients = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private Collection<Catalogue> catalogues = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private Collection<DeliveryMan> deliveryMEN = new ArrayList<>();
}
