package nl.svb.dms.ddd_lease_api.sales.application.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/quotes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
class QuoteRestController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteResponse> fillOutQuote(@Valid @RequestBody FillOutQuoteRequest request) throws QuoteNotFoundException {

        log.debug("received FillOutQuoteRequest: {}", request);

        final var fillOutQuoteResponse = quoteService.fillOutQuote(request);

        log.debug("Returning FillOutQuoteResponse: {}", fillOutQuoteResponse);

        return switch (fillOutQuoteResponse.quoteStatus()) {
            case FILLED_OUT -> ResponseEntity.created(fillOutQuoteResponse.uri())
                    .body(fillOutQuoteResponse);
            case REJECTED_MIN_AGE, REJECTED_MIN_INCOME -> ResponseEntity.badRequest()
                    .body(fillOutQuoteResponse);
            default -> ResponseEntity.unprocessableEntity().build();
        };
    }

    @PatchMapping("/calculate/{quoteReference}")
    public ResponseEntity<QuoteResponse> calculateInstallment(@PathVariable("quoteReference") UUID quoteReference)
            throws QuoteNotFoundException {

        log.debug("received CalculateInstallmentRequest: {}", quoteReference);

        final var installmentCalculatedResponse = quoteService.calculateInstallment(quoteReference);

        log.debug("Returning CalculateInstallmentResponse: {}", installmentCalculatedResponse);

        return switch (installmentCalculatedResponse.quoteStatus()) {
            case CALCULATED -> ResponseEntity.noContent()
                    .eTag(installmentCalculatedResponse.uri().getPath())
                    .build();
            case REJECTED_MAX_PERCENTAGE_OF_YEARLY_INCOME -> ResponseEntity.badRequest()
                    .body(installmentCalculatedResponse);
            default -> ResponseEntity.unprocessableEntity().build();
        };
    }

    @PatchMapping("/sign/{quoteReference}/signature/{signature}")
    public ResponseEntity<QuoteResponse> signQuote(@PathVariable("quoteReference") UUID quoteReference,
                                                   @PathVariable("signature") String signature)
            throws QuoteNotFoundException {

        log.debug("received signQuoteRequest for quoteReference: {}, signature: {}",
                quoteReference, signature);

        final var signQuoteResponse = quoteService.signQuote(quoteReference, signature);

        log.debug("Returning signQuoteResponse: {}", signQuoteResponse);

        return switch (signQuoteResponse.quoteStatus()) {
            case CALCULATED -> ResponseEntity.noContent()
                    .eTag(signQuoteResponse.uri().getPath())
                    .build();
            case REJECTED_BKR_REGISTRATION -> ResponseEntity.badRequest().body(signQuoteResponse);
            default -> ResponseEntity.unprocessableEntity().build();
        };
    }
}
