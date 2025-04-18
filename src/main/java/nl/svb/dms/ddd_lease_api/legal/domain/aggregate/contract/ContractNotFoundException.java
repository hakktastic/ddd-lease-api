package nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract;

public class ContractNotFoundException extends Exception {

  public ContractNotFoundException(ContractReference contractReference) {
    super("Unable to find contract with reference " + contractReference.toString());
  }
}