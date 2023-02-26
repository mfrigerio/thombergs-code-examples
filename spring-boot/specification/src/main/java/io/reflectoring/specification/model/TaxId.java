package io.reflectoring.specification.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class TaxId {

    private String vatNumber;
}
