package nl.svb.dms.ddd_lease_api.legal.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import org.jmolecules.event.annotation.DomainEvent;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
@DomainEvent
public class FillOutContractCommand implements LegalCommand {

  private final ContractReference contractReference;
}