package nl.svb.dms.ddd_lease_api.legal.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.ContractDomainService;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class EventListenerContractFilledOut {

  private final ContractDomainService contractDomainService;

  @ApplicationModuleListener
  void on(ContractFilledOutEvent contractFilledOutEvent) {
    log.info("Contract filled out event received: {}", contractFilledOutEvent);
    contractDomainService.checkCreditRating(
        contractFilledOutEvent.getContract().getContractEntity().getContractReference());
  }
}