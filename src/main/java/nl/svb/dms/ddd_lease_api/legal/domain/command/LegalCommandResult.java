package nl.svb.dms.ddd_lease_api.legal.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractStatus;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEvent;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class LegalCommandResult {

    private final Contract contract;
    private final ContractStatus contractStatus;
    private final LegalEvent legalEvent;
}
