package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import java.time.LocalDate;
import java.util.UUID;
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
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerBirthDate;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerEmail;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerFirstName;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerLastName;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.CustomerYearlyIncome;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "contracts")
class ContractDocument {

  @Id
  private ObjectId _id;

  private UUID contractReference;
  private UUID quoteReference;
  private ContractStatus contractStatus;
  private Double creditRating;

  private Long leaseDuration;
  private Integer leaseMileage;
  private Double leasePrice;

  private String customerFirstName;
  private String customerLastName;
  private String customerEmail;
  private LocalDate customerBirthDate;
  private Double customerYearlyIncome;

  private String carBrandName;
  private String carModel;
  private Double carCatalogPrice;

  @Version
  private int revision;

  static ContractDocument from(Contract contract) {

    final var contractDomainEntity = contract.getContractEntity();

    final var contractJpaEntity = new ContractDocument();
    contractJpaEntity.setContractReference(
        contractDomainEntity.getContractReference().contractReference());
    contractJpaEntity.setQuoteReference(contractDomainEntity.getQuoteReference().quoteReference());
    contractJpaEntity.setContractStatus(contractDomainEntity.getContractStatus());
    contractJpaEntity.setCreditRating(contractDomainEntity.getCreditRating().creditRating());
    contractJpaEntity.setLeaseDuration(contractDomainEntity.getLeaseDuration().leaseDuration());
    contractJpaEntity.setLeaseMileage(contractDomainEntity.getLeaseMileage().leaseMileage());
    contractJpaEntity.setLeasePrice(contractDomainEntity.getLeasePrice().leasePrice());
    contractJpaEntity.setCustomerFirstName(
        contractDomainEntity.getCustomerFirstName().customerFirstName());
    contractJpaEntity.setCustomerLastName(
        contractDomainEntity.getCustomerLastName().customerLastName());
    contractJpaEntity.setCustomerEmail(contractDomainEntity.getCustomerEmail().customerEmail());
    contractJpaEntity.setCustomerBirthDate(
        contractDomainEntity.getCustomerBirthDate().customerBirthDate());
    contractJpaEntity.setCustomerBirthDate(
        contractDomainEntity.getCustomerBirthDate().customerBirthDate());
    contractJpaEntity.setCustomerYearlyIncome(
        contractDomainEntity.getCustomerYearlyIncome().customerYearlyIncome());
    contractJpaEntity.setCarBrandName(contractDomainEntity.getCarBrand().carBrand());
    contractJpaEntity.setCarModel(contractDomainEntity.getCarModel().carModel());
    contractJpaEntity.setCarCatalogPrice(
        contractDomainEntity.getCarCatalogPrice().carCatalogPrice());

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