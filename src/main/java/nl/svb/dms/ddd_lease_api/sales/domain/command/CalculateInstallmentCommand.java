package nl.svb.dms.ddd_lease_api.sales.domain.command;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.jmolecules.event.annotation.DomainEvent;

@ToString
@RequiredArgsConstructor(staticName = "of")
@DomainEvent
public class CalculateInstallmentCommand implements SalesCommand {
}