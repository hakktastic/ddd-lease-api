package nl.svb.dms.ddd_lease_api.legal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;

import java.util.UUID;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class SpringCreditRatingCheckedEvent {

    private final UUID contractReference;

    public static SpringCreditRatingCheckedEvent from(CreditRatingCheckedEvent creditRatingCheckedEvent) {
        return new SpringCreditRatingCheckedEvent(
                creditRatingCheckedEvent.getContract().getContractEntity().getContractReference().contractReference());
    }
}