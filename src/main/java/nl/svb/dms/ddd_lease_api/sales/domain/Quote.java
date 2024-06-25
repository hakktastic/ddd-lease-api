package nl.svb.dms.ddd_lease_api.sales.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Quote {

    private QuoteEntity quoteEntity;
}
