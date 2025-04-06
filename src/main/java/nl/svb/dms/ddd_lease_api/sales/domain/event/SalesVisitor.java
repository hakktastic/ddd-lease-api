package nl.svb.dms.ddd_lease_api.sales.domain.event;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import org.jmolecules.event.annotation.DomainEventPublisher;

/**
 * Visitor Design Pattern.
 *
 * @see <a href="https://www.baeldung.com/java-visitor-pattern">Baeldung - Visitor Dessign Pattern in Java</ahref>
 */
public interface SalesVisitor {

    @DomainEventPublisher(publishes = "Event that is published when a Quote is filled out", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException;

    @DomainEventPublisher(publishes = "Event that is published when a Quote installment is calculated", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(InstallmentCalculatedEvent installmentCalculatedEvent);

    @DomainEventPublisher(publishes = "Event that is published when a Quote is signed", type = DomainEventPublisher.PublisherType.INTERNAL)
    void visit(QuoteSignedEvent quoteSignedEvent);

}