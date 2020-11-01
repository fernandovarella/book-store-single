package com.fernando.bookstore.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUtil {

    private static final Map<String, Direction> directionMap = new HashMap<>();
    static {
        directionMap.put("asc", Direction.ASC);
        directionMap.put("desc", Direction.DESC);
    }

    private RepositoryUtil () { }
    
    /**
     * Build a {@link PageRequest} object for the given request parameters
     * @param page  the page number
     * @param size  the page size
     * @param sort  a list of sort parameters, each in the format "column,direction" (ie. sort=id,asc&sort=title,desc)
     * @return
     */
    public static PageRequest buildPageableFromRequest(Integer page, Integer size, String[] sort) {
        if (sort[0].contains(",")) {
            List<Order> orders = new ArrayList<>();
            for (String s : sort) {
                orders.add(buildOrder(s));
            }
            return PageRequest.of(page, size, Sort.by(orders));
        } else {
            return PageRequest.of(page, size, Sort.by(sort[1], sort[0]));
        }
    }

    private static Order buildOrder(String sort) {
        String[] parts = sort.split(",");
        return new Order(directionMap.get(parts[1]), parts[0], NullHandling.NULLS_LAST);
    }

}
