package nl.svb.dms.ddd_lease_api.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.stereotype.Repository;

@AnalyzeClasses(packages = "nl.svb.dms.ddd_lease_api")
public class HypersistenceTest {

  /**
   * Make sure only {@link BaseJpaRepository} from the Hypersistence Utils is used for
   * {@link Repository} interfaces.
   */
  @ArchTest
  private final ArchRule all_classes_annotated_with_spring_repository_should_extend_BaseJpaRepository = classes()
      .that()
      .areAnnotatedWith(Repository.class)
      .should()
      .beInterfaces()
      .andShould()
      .beAssignableTo(BaseJpaRepository.class);
}