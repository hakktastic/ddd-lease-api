package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car;

public record CarModel(String carModel) {

  public static CarModel of(String carModel) {
    return new CarModel(carModel);
  }
}