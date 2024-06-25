package nl.svb.dms.ddd_lease_api.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class QuoteEntity {

    private Duration duration;

    private Mileage mileage;

    private LeasePrice leasePrice;

    private Car car;

    private Customer customer;
}
