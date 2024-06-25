package nl.svb.dms.ddd_lease_api.sales.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "of")
public class Car {

    @NotNull
    private String car;
}
