package com.example.ruleEngine9.service;

import com.example.ruleEngine9.listener.AuditingAgendaEventListener;
import com.example.ruleEngine9.listener.ToStringAgendaEventListener;
import com.example.ruleEngine9.ruleUnit.GreetingUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    public List<String> validGreetingString(String greetingString, Integer length) {

        GreetingUnit greetingUnit = new GreetingUnit();
        greetingUnit.getStrings().add(greetingString);
        greetingUnit.getInts().add(length);

        final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();
        ruleConfig.getAgendaEventListeners().add(new AuditingAgendaEventListener());
        ruleConfig.getAgendaEventListeners().add(new ToStringAgendaEventListener());
        List<String> result = null;
        try {
            RuleUnitInstance<GreetingUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(greetingUnit, ruleConfig);
            instance.fire();
            result = greetingUnit.getResults();
            instance.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;

    }
}
