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
public class Catalogue {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private int duration;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Category> categories = new ArrayList<>();
}
