package com.sp.infrastructure

import org.flywaydb.test.annotation.FlywayTest
import org.flywaydb.test.junit5.annotation.FlywayTestExtension
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Repository
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

/**
 * @author Jaedoo Lee
 */
@DataJpaTest
@FlywayTestExtension
@FlywayTest(invokeCleanDB = false)
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(useDefaultFilters = false, includeFilters = [ComponentScan.Filter(Repository::class)])
abstract class TestDataSupport
