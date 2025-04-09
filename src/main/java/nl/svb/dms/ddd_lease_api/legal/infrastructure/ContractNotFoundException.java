package nl.svb.dms.ddd_lease_api.legal.infrastructure;


import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;

class ContractNotFoundException extends Exception {

    public ContractNotFoundException(ContractReference contractReference) {
        super("Unable to find contract with reference " + contractReference.toString());
    }
}