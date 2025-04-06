package nl.svb.dms.ddd_lease_api.legal.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarBrand;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarCatalogPrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.car.CarModel;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractStatus;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.CreditRating;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.customer.*;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseDuration;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeaseMileage;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.lease.LeasePrice;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.quote.QuoteReference;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Getter
@ToString
@AllArgsConstructor(staticName = "of")
@Entity
public class ContractEntity {

    public static final Double MIN_REQUIRED_CREDIT_RATING = 80.0;

    @Identity
    private final QuoteReference quoteReference;

    private final LeaseDuration leaseDuration;
    private final LeaseMileage leaseMileage;
    private final CustomerFirstName customerFirstName;
    private final CustomerLastName customerLastName;
    private final CustomerEmail customerEmail;
    private final CustomerBirthDate customerBirthDate;
    private final CustomerYearlyIncome customerYearlyIncome;
    private final CarBrand carBrand;
    private final CarModel carModel;
    private final CarCatalogPrice carCatalogPrice;
    private final LeasePrice leasePrice;
    private CustomerSignature customerSignature;
    @Setter
    private ContractStatus contractStatus;
    private CreditRating creditRating;

    public void addCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }
    
    public void addCustomerSignature(CustomerSignature customerSignature) {
        this.customerSignature = customerSignature;
    }

    public Boolean hasCustomerValidCreditRating() {
        return this.creditRating.creditRating() >= MIN_REQUIRED_CREDIT_RATING;
    }
}