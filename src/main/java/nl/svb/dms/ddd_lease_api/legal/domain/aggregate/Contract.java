package nl.svb.dms.ddd_lease_api.legal.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.legal.domain.aggregate.contract.ContractStatus;
import nl.svb.dms.ddd_lease_api.legal.domain.command.CheckCreditRatingCommand;
import nl.svb.dms.ddd_lease_api.legal.domain.command.FillOutContractCommand;
import nl.svb.dms.ddd_lease_api.legal.domain.command.LegalCommand;
import nl.svb.dms.ddd_lease_api.legal.domain.command.LegalCommandResult;
import nl.svb.dms.ddd_lease_api.legal.domain.event.ContractFilledOutEvent;
import nl.svb.dms.ddd_lease_api.legal.domain.event.CreditRatingCheckedEvent;
import org.jmolecules.event.annotation.DomainEventHandler;

@Slf4j
@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Contract {

  private final ContractEntity contractEntity;

  @DomainEventHandler
  public LegalCommandResult handleCommand(FillOutContractCommand fillOutContractCommand) {

    logCommand(fillOutContractCommand);
    return fillOutContract();
  }

  @DomainEventHandler
  public LegalCommandResult handleCommand(CheckCreditRatingCommand checkCreditRatingCommand) {

    logCommand(checkCreditRatingCommand);
    contractEntity.addCreditRating(checkCreditRatingCommand.getCreditRating());

    if (!contractEntity.hasCustomerValidCreditRating()) {
      return rejectContract(ContractStatus.REJECTED_CREDIT_SCORE);
    }
    return checkCreditRating();
  }

  private LegalCommandResult rejectContract(ContractStatus reasonForRejection) {
    contractEntity.setContractStatus(reasonForRejection);
    log.warn("contract rejected with reason: {}", reasonForRejection);
    return LegalCommandResult.of(this, reasonForRejection, null);
  }

  private LegalCommandResult fillOutContract() {
    contractEntity.setContractStatus(ContractStatus.FILLED_OUT);
    return LegalCommandResult.of(this, ContractStatus.FILLED_OUT, new ContractFilledOutEvent(this));
  }

  private LegalCommandResult checkCreditRating() {
    contractEntity.setContractStatus(ContractStatus.ACCEPTED);
    return LegalCommandResult.of(this, ContractStatus.ACCEPTED, new CreditRatingCheckedEvent(this));
  }

  private void logCommand(LegalCommand legalCommand) {
    log.debug("handling command: {}", legalCommand.toString());
  }
}