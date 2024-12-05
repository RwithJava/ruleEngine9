package com.example.ruleEngine9;

import com.example.ruleEngine9.listener.AuditingAgendaEventListener;
import com.example.ruleEngine9.listener.ToStringAgendaEventListener;
import com.example.ruleEngine9.ruleUnit.GreetingUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RuleEngine9ApplicationTests {

	@Test
	public void lengthGreaterThan5() {

		GreetingUnit unit = new GreetingUnit();
		unit.getStrings().add("Hello World");
		unit.getInts().add(11);

		final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
		ruleConfig.getAgendaEventListeners().add(new AuditingAgendaEventListener());
		ruleConfig.getAgendaEventListeners().add(new ToStringAgendaEventListener());
		try (RuleUnitInstance<GreetingUnit> unitInstance = RuleUnitProvider.get().createRuleUnitInstance(unit,
				ruleConfig)) {
			assertThat(unitInstance.fire()).isEqualTo(3);
//            log.info("results:{}", unit.getResults());
			assertThat(unit.getResults()).contains("it worked!");
			assertThat(unit.getResults()).contains("it also worked with HELLO WORLD");
			assertThat(unit.getResults()).doesNotContain("this shouldn't fire");
			assertThat(unit.getResults()).contains("String 'Hello World' is 11 characters long");
		}
	}

}
