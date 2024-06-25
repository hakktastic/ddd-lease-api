package nl.svb.dms.ddd_lease_api.sales.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.svb.dms.ddd_lease_api.sales.domain.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/quotes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
class QuoteRestController {

    private final QuoteService quoteService;

    @PostMapping
    ResponseEntity<QuoteDTO> fillOut(@RequestBody QuoteDTO quoteDTO) {

        final var filledOutQuote = quoteService.fillOut(quoteDTO.toQuote()).orElseThrow();
        final var quoteResponseDTO = QuoteDTO.from(filledOutQuote);

        return new ResponseEntity<>(quoteResponseDTO, HttpStatus.CREATED);
    }
}
