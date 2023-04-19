package com.example.counter.dtos;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Set;

public record OrderPlacingRequest(String customerName, String customerAddress,
                                  Set<MenuItemDTO> menuItems) {

    public OrderPlacingRequest {
        Assert.isTrue(StringUtils.hasLength(customerName), "customer name is not empty");
        Assert.isTrue(StringUtils.hasLength(customerAddress), "customer address is not empty");
        Assert.notEmpty(menuItems, "menu items must have data");
    }
}
