package nl.svb.dms.ddd_lease_api.legal.domain.event;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainEventPublisher;

@RequiredArgsConstructor(staticName = "of")
public class LegalEventPublishVisitor implements LegalVisitor {

    private final ContractDomainEventPublisher publisher;

    @Override
    public void visit(ContractFilledOutEvent contractFilledOutEvent) {
        publisher.publish(contractFilledOutEvent);
    }

    @Override
    public void visit(ContractSignedEvent contractSignedEvent) {
        publisher.publish(contractSignedEvent);
    }

    @Override
    public void visit(CreditRatingCheckedEvent creditRatingCheckedEvent) {
        publisher.publish(creditRatingCheckedEvent);
    }
}
