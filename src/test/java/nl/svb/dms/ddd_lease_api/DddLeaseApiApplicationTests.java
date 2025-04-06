package nl.svb.dms.ddd_lease_api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class DddLeaseApiApplicationTests {

    private ApplicationModules modules;

    @BeforeEach
    void beforeEach(){
        modules = ApplicationModules.of(DddLeaseApiApplication.class);
    }

    @Test
    void verifyModules() {

        modules.forEach(applicationModule -> log.info("Module: '{}', package: '{}'", applicationModule.getIdentifier(),
            applicationModule.getBasePackage()));

        modules.verify();


    }

    @Test
    void writeDocumentation(){

        final var documenter = new Documenter(modules).writeDocumentation()
            .writeModuleCanvases()
            .writeIndividualModulesAsPlantUml()
            .writeModulesAsPlantUml();

        assertThat(documenter).isNotNull();
    }
}