package nl.svb.dms.ddd_lease_api.legal.domain;

import lombok.RequiredArgsConstructor;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.ContractEntity;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractProvider;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractStatus;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.CreditRating;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;
import nl.svb.dms.ddd_lease_api.legal.domain.command.CheckCreditRatingCommand;
import nl.svb.dms.ddd_lease_api.legal.domain.command.FillOutContractCommand;
import nl.svb.dms.ddd_lease_api.legal.domain.command.LegalCommandResult;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEventPublishVisitor;
import nl.svb.dms.ddd_lease_api.legal.domain.event.LegalEventSaveVisitor;

@RequiredArgsConstructor
public class ContractDomainService {

    private final ContractProvider contractProvider;
    private final LegalEventSaveVisitor legalEventSaveVisitor;
    private final LegalEventPublishVisitor legalEventPublishVisitor;
    private final ContractDomainRepository contractDomainRepository;

    public LegalCommandResult fillOut(LeaseDuration duration, LeaseMileage mileage, CustomerFirstName customerFirstName,
                                      CustomerLastName customerLastName, CustomerEmail customerEmail,
                                      CustomerBirthDate customerBirthDate, CustomerYearlyIncome customerYearlyIncome,
                                      CarBrand brandName, CarModel model, CarCatalogPrice carCatalogPrice,
                                      LeasePrice leasePrice, QuoteReference quoteReference) {

        final var contract = Contract.of(ContractReference.of(), ContractEntity.of(quoteReference, duration, mileage,
                customerFirstName, customerLastName, customerEmail, customerBirthDate, customerYearlyIncome, brandName,
                model, carCatalogPrice, leasePrice, ContractStatus.CREATED, CreditRating.of(0.0)));

        final var fillOutContractResult = contract.handleCommand(FillOutContractCommand.of(
                contract.getContractReference()));

        return acceptLegalEventVisitor(fillOutContractResult);
    }

    public LegalCommandResult checkCreditRating(ContractReference contractReference) {

        final var contract = contractProvider.findContract(contractReference);
        final var creditRating = contractDomainRepository.getCreditRating(contractReference);
        final var creditRatingCheckedResult = contract.handleCommand(
                CheckCreditRatingCommand.of(contractReference, CreditRating.of(creditRating)));

        return acceptLegalEventVisitor(creditRatingCheckedResult);
    }

    private LegalCommandResult acceptLegalEventVisitor(LegalCommandResult legalCommandResult) {

        final var legalEvent = legalCommandResult.getLegalEvent();

        if (legalEvent != null) {
            legalEvent.accept(legalEventSaveVisitor);
            legalEvent.accept(legalEventPublishVisitor);
        }

        return legalCommandResult;
    }
}