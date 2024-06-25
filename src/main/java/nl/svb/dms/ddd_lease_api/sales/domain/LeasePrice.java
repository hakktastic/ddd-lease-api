package nl.svb.dms.ddd_lease_api.sales.domain;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class LeasePrice {

    @PositiveOrZero
    private Double leasePrice;
}
