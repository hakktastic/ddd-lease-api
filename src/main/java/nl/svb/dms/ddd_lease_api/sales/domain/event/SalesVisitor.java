package nl.svb.dms.ddd_lease_api.sales.domain.event;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;

/**
 * Visitor Design Pattern.
 *
 * @see <a href="https://www.baeldung.com/java-visitor-pattern">Baeldung - Visitor Dessign Pattern in Java</ahref>
 */
public interface SalesVisitor {

    void visit(QuoteFilledOutEvent quoteFilledOutEvent) throws QuoteNotFoundException;

    void visit(InstallmentCalculatedEvent installmentCalculatedEvent);

    void visit(QuoteSignedEvent quoteSignedEvent);

}
