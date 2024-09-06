package nl.svb.dms.ddd_lease_api.legal.domain.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerSignature;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class SignContractCommand implements LegalCommand {

    private final ContractReference contractReference;
    private final CustomerSignature customerSignature;
}
