package nl.svb.dms.ddd_lease_api.sales.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Quote {

    @Positive
    private Long duration;

    @Positive
    private long mileage;

    @PositiveOrZero
    private Double leasePrice;

    @NotNull
    private String car;

    @NotNull
    private String customer;
}
