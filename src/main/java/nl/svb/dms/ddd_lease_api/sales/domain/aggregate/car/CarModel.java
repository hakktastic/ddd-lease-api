package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record CarModel(String carModel) {

    public static CarModel of(String carModel) {
        return new CarModel(carModel);
    }
}