package nl.svb.dms.ddd_lease_api.sales.domain;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Duration {

    @Positive
    private Long duration;
}
