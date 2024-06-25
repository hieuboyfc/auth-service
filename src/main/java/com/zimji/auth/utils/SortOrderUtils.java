package com.zimji.auth.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortOrderUtils<E extends Sort.Order> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortOrderUtils.class);

    public static List<Sort.Order> getSortOrders(String sortBy, String sortField) {
        List<Sort.Order> orders = new ArrayList<>();
        List<String> sortFields = List.of("createDate");
        try {
            if (ObjectUtils.isNotEmpty(sortField)) {
                sortFields = Arrays.asList(sortField
                        .replace(Constants.Symbol.SPACE, Constants.Symbol.EMPTY)
                        .split(Constants.Symbol.COMMA)
                );
            }
            if (ObjectUtils.isEmpty(sortBy)) {
                sortBy = Constants.Symbol.ASC;
            }
            switch (sortBy) {
                case Constants.Symbol.ASC:
                    sortFields.forEach(item -> {
                        orders.add(new Sort.Order(Sort.Direction.ASC, item));
                    });
                    break;
                case Constants.Symbol.DESC:
                    sortFields.forEach(item -> {
                        orders.add(new Sort.Order(Sort.Direction.DESC, item));
                    });
                    break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return orders;
    }

}
