package nl.svb.dms.ddd_lease_api.legal.domain.event;

/**
 * Visitor Design Pattern.
 *
 * @see <a href="https://www.baeldung.com/java-visitor-pattern">Baeldung - Visitor Dessign Pattern in Java</ahref>
 */
public interface LegalVisitor {

    void visit(ContractFilledOutEvent contractFilledOutEvent);

    void visit(ContractSignedEvent contractSignedEvent);

    void visit(CreditRatingCheckedEvent creditRatingCheckedEvent);
}
