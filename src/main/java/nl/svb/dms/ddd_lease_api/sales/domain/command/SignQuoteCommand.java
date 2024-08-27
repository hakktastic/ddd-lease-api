package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.customer.CustomerSignature;

@ToString
@RequiredArgsConstructor(staticName = "of")
public class SignQuoteCommand implements SalesCommand {

    private final CustomerSignature customerSignature;
}
