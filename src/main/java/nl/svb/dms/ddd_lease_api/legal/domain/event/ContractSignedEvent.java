package nl.svb.dms.ddd_lease_api.legal.domain.event;

import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;

public class ContractSignedEvent extends LegalEvent {

    public ContractSignedEvent(Contract contract) {
        super(contract);
    }

    @Override
    public void accept(LegalVisitor legalVisitor) {
        legalVisitor.visit(this);
    }
}
