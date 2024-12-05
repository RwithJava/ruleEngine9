package com.example.ruleEngine9.service;

import com.example.ruleEngine9.model.OrderDiscount;
import com.example.ruleEngine9.model.OrderRequest;
import com.example.ruleEngine9.ruleUnit.OrderUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.drools.ruleunits.api.conf.RuleConfig;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public OrderDiscount validOrderRequest(OrderRequest orderRequest) {

        OrderUnit orderUnit = new OrderUnit();
        orderUnit.getOrderRequest().add(orderRequest);

        final RuleConfig ruleConfig = RuleUnitProvider.get().newRuleConfig();

        OrderDiscount orderDiscount = null;
        try {
            RuleUnitInstance<OrderUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(orderUnit, ruleConfig);
            instance.fire();
            orderDiscount = orderUnit.getOrderDiscount();
            instance.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return orderDiscount;
    }
}
