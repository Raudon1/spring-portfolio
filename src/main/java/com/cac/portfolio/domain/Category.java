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
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Products> products = new ArrayList<>();
}
