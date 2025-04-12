package nl.svb.dms.ddd_lease_api.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "nl.svb.dms.ddd_lease_api")
public class QuoteDomainServiceAccessRuleTest {

    /**
     * Normally, the domain should have the validation for the entities/value objects.
     * Because I implemented input validation (jakarta) in the package nl.svb.dms.ddd_lease_api.sales.application.rest,
     * I wanted to test if I can force with ArchUnit that only the domain service is accessed by the rest package.
     */
    @ArchTest
    private final ArchRule quoteDomainServiceShouldOnlyBeAccessedByRestLayer =
            noClasses()
                    .that()
                    .resideOutsideOfPackage("nl.svb.dms.ddd_lease_api.sales.application.rest")
                    .should()
                    .accessClassesThat()
                    .haveSimpleName("QuoteDomainService")
                    .andShould()
                    .haveFullyQualifiedName("nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService.fillOut")
                    .andShould()
                    .haveFullyQualifiedName("nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService.calculateInstallment")
                    .andShould()
                    .haveFullyQualifiedName("nl.svb.dms.ddd_lease_api.sales.domain.QuoteDomainService.sign");
}