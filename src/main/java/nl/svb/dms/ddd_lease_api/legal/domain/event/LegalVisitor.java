package nl.svb.dms.ddd_lease_api.legal.domain.event;

import org.jmolecules.event.annotation.DomainEventPublisher;

/**
 * Visitor Design Pattern.
 *
 * @see <a href="https://www.baeldung.com/java-visitor-pattern">Baeldung - Visitor Dessign Pattern in Java</ahref>
 */
public interface LegalVisitor {

    @DomainEventPublisher(publishes = "Event that is published when a Contract is filled out", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(ContractFilledOutEvent contractFilledOutEvent);

    @DomainEventPublisher(publishes = "Event that is published when a Contract is signed", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(ContractSignedEvent contractSignedEvent);

    @DomainEventPublisher(publishes = "Event that is published when a Contract Credit Rating is checked", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(CreditRatingCheckedEvent creditRatingCheckedEvent);
}