package com.abraham.cart.domain;

import java.math.BigDecimal;

public record Item (Integer itemId, String name, String description, BigDecimal price) {};
