package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car;

public record CarCatalogPrice(Double carCatalogPrice) {

    public static CarCatalogPrice of(Double carCatalogPrice) {
        return new CarCatalogPrice(carCatalogPrice);
    }
}