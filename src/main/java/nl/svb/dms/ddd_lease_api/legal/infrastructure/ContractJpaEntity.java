package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.Contract;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.ContractEntity;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractReference;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractStatus;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.CreditRating;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "contract")
class ContractJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID contractReference;
    private UUID quoteReference;
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
    private Double creditRating;

    private Long leaseDuration;
    private Integer leaseMileage;
    private Double leasePrice;

    // TODO create new JPA entity for customer
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private LocalDate customerBirthDate;
    private Double customerYearlyIncome;

    // TODO create new JPA entity for car
    private String carBrandName;
    private String carModel;
    private Double carCatalogPrice;

    static ContractJpaEntity from(Contract contract) {

        final var contractDomainEntity = contract.getContractEntity();

        final var contractJpaEntity = new ContractJpaEntity();
        contractJpaEntity.setContractReference(contractDomainEntity.getContractReference().contractReference());
        contractJpaEntity.setQuoteReference(contractDomainEntity.getQuoteReference().quoteReference());
        contractJpaEntity.setContractStatus(contractDomainEntity.getContractStatus());
        contractJpaEntity.setCreditRating(contractDomainEntity.getCreditRating().creditRating());
        contractJpaEntity.setLeaseDuration(contractDomainEntity.getLeaseDuration().leaseDuration());
        contractJpaEntity.setLeaseMileage(contractDomainEntity.getLeaseMileage().leaseMileage());
        contractJpaEntity.setLeasePrice(contractDomainEntity.getLeasePrice().leasePrice());
        contractJpaEntity.setCustomerFirstName(contractDomainEntity.getCustomerFirstName().customerFirstName());
        contractJpaEntity.setCustomerLastName(contractDomainEntity.getCustomerLastName().customerLastName());
        contractJpaEntity.setCustomerEmail(contractDomainEntity.getCustomerEmail().customerEmail());
        contractJpaEntity.setCustomerBirthDate(contractDomainEntity.getCustomerBirthDate().customerBirthDate());
        contractJpaEntity.setCustomerBirthDate(contractDomainEntity.getCustomerBirthDate().customerBirthDate());
        contractJpaEntity.setCustomerYearlyIncome(contractDomainEntity.getCustomerYearlyIncome().customerYearlyIncome());
        contractJpaEntity.setCarBrandName(contractDomainEntity.getCarBrand().carBrand());
        contractJpaEntity.setCarModel(contractDomainEntity.getCarModel().carModel());
        contractJpaEntity.setCarCatalogPrice(contractDomainEntity.getCarCatalogPrice().carCatalogPrice());

        return contractJpaEntity;
    }

    Contract toContract() {

        return Contract.of(ContractEntity.of(
                        ContractReference.of(contractReference),
                        QuoteReference.of(this.quoteReference),
                        LeaseDuration.of(this.leaseDuration),
                        LeaseMileage.of(this.leaseMileage),
                        CustomerFirstName.of(this.customerFirstName),
                        CustomerLastName.of(this.customerLastName),
                        CustomerEmail.of(this.customerEmail),
                        CustomerBirthDate.of(this.customerBirthDate),
                        CustomerYearlyIncome.of(this.customerYearlyIncome),
                        CarBrand.of(this.carBrandName),
                        CarModel.of(this.carModel),
                        CarCatalogPrice.of(this.carCatalogPrice),
                        LeasePrice.of(this.leasePrice),
                        this.contractStatus,
                        CreditRating.of(this.creditRating)
                )
        );
    }
}