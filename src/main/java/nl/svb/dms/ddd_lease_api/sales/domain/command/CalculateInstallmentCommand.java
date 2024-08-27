package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(staticName = "of")
public class CalculateInstallmentCommand implements SalesCommand {
}
