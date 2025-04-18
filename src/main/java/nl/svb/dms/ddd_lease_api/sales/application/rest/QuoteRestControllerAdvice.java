package nl.svb.dms.ddd_lease_api.sales.application.rest;

import nl.svb.dms.ddd_lease_api.sales.domain.aggregate.quote.QuoteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class QuoteRestControllerAdvice {

  @ExceptionHandler(QuoteNotFoundException.class)
  public ResponseEntity<Void> handleNotFoundExceptions(Exception exception) {
    return ResponseEntity.notFound().build();
  }
}