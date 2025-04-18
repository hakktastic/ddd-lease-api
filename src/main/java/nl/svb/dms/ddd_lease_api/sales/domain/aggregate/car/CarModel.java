package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.car;

public record CarModel(String carModel) {

  public static CarModel of(String carModel) {
    return new CarModel(carModel);
  }
}