package nl.svb.dms.ddd_lease_api.purchase.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.SpringCreditRatingCheckedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListenerCreditRatingChecked {

    @EventListener
    void on(SpringCreditRatingCheckedEvent creditRatingCheckedEvent) {
        log.info("Credit rating checked event received: {}", creditRatingCheckedEvent);
    }
}