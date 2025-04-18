package nl.svb.dms.ddd_lease_api.purchase.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.SpringCreditRatingCheckedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class EventListenerCreditRatingChecked {

  @ApplicationModuleListener
  void on(SpringCreditRatingCheckedEvent creditRatingCheckedEvent) {
    log.info("Credit rating checked event received: {}", creditRatingCheckedEvent);
  }
}