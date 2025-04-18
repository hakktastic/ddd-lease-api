package nl.svb.dms.ddd_lease_api.sales.application.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

record FillOutQuoteRequest(
    @Min(0)
    Long leaseDuration,
    @Min(0)
    Integer mileage,
    @NotBlank
    String customerFirstname,
    @NotBlank
    String customerLastname,
    @Email
    String customerEmail,
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    LocalDate customerBirthDate,
    @Min(0)
    Double customerYearlyIncome,
    @NotBlank
    String carBrand,
    @NotBlank
    String carModel,
    @Min(0)
    Double carCatalogPrice) {

}