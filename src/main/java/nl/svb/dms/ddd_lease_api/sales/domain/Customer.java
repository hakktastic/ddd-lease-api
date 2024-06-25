package nl.svb.dms.ddd_lease_api.sales.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Customer {

    @NotNull
    private String customer;
}
