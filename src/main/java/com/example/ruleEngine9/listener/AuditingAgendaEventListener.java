package com.example.ruleEngine9.listener;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.rule.Match;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuditingAgendaEventListener extends DefaultAgendaEventListener {

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        final Match match = event.getMatch();
        final StringBuilder sb = new StringBuilder("\nObjects:").append(match.getObjects());

        final Rule rule = event.getMatch().getRule();
        sb.append("\nRule fired: ").append(rule.getName());
        retrieveMetaDataMap(rule, sb);

        System.out.println(sb.toString());
    }

    private void retrieveMetaDataMap(Rule rule, final StringBuilder sb) {
        final Map<String, Object> ruleMetaDataMap = rule.getMetaData();
        if (!ruleMetaDataMap.isEmpty()) {
            sb.append("\n  With [").append(ruleMetaDataMap.size()).append("] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=").append(key).append(", value=").append(ruleMetaDataMap.get(key));
            }
        }
    }
}
