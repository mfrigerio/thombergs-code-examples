package io.reflectoring.specification.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Distributor {
    @Id
    private String id;

    private String name;

    @OneToOne
    private Address address;

    @ManyToMany
    private Set<Address> secondaryAddresses;

    @Embedded
    private TaxId taxId;

}
