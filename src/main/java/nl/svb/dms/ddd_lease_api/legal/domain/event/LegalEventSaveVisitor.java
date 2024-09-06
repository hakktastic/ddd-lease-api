package nl.svb.dms.ddd_lease_api.legal.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainRepository;

@RequiredArgsConstructor(staticName = "of")
public class LegalEventSaveVisitor implements LegalVisitor {

    private final ContractDomainRepository contractDomainRepository;

    @Override
    public void visit(ContractFilledOutEvent contractFilledOutEvent) {
        contractDomainRepository.save(contractFilledOutEvent);
    }

    @Override
    public void visit(ContractSignedEvent contractSignedEvent) {
        contractDomainRepository.save(contractSignedEvent);
    }

    @Override
    public void visit(CreditRatingCheckedEvent creditRatingCheckedEvent) {
        contractDomainRepository.save(creditRatingCheckedEvent);
    }
}
