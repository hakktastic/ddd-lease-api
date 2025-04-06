package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CarCatalogPrice(Double carCatalogPrice) {

    public static CarCatalogPrice of(Double carCatalogPrice) {
        return new CarCatalogPrice(carCatalogPrice);
    }
}