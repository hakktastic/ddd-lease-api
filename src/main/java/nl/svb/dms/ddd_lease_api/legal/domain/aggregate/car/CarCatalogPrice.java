package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car;

public record CarCatalogPrice(Double carCatalogPrice) {

    public static CarCatalogPrice of(Double carCatalogPrice) {
        return new CarCatalogPrice(carCatalogPrice);
    }
}