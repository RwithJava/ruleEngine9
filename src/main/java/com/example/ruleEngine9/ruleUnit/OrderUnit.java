package com.example.ruleEngine9.ruleUnit;

import com.example.ruleEngine9.model.OrderDiscount;
import com.example.ruleEngine9.model.OrderRequest;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

public class OrderUnit implements RuleUnitData {

    private final DataStore<OrderRequest> orderRequest;
    private final OrderDiscount orderDiscount = new OrderDiscount();

    public OrderUnit() {
        this(DataSource.createStore());
    }

    public OrderUnit(DataStore<OrderRequest> orderRequest) {
        this.orderRequest = orderRequest;
    }

    public DataStore<OrderRequest> getOrderRequest() {
        return orderRequest;
    }

    public OrderDiscount getOrderDiscount() {
        return orderDiscount;
    }
}
