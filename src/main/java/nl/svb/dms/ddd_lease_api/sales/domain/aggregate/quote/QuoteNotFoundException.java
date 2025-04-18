package nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote;

public class QuoteNotFoundException extends Exception {

  public QuoteNotFoundException(QuoteReference quoteReference) {
    super("Unable to find quote with reference " + quoteReference.toString());
  }
}