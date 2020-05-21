package com.migueltdasilva.jhipstertest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.migueltdasilva.jhipstertest");

        noClasses()
            .that()
            .resideInAnyPackage("com.migueltdasilva.jhipstertest.service..")
            .or()
            .resideInAnyPackage("com.migueltdasilva.jhipstertest.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.migueltdasilva.jhipstertest.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
