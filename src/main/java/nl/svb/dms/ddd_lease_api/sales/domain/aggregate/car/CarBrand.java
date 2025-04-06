package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CarBrand(String carBrand) {

    public static CarBrand of(String carBrand) {
        return new CarBrand(carBrand);
    }
}