package com.xiaoxue.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.xiaoxue.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query<T> extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private Page<T> page;

    private int currPage = 1;

    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        if (params.get("page") != null) {
            currPage = Integer.parseInt((String) params.get("page"));
        }
        if (params.get("limit") != null) {
            limit = Integer.parseInt((String) params.get("limit"));
        }

        this.put("offset", (currPage - 1) * limit);
        this.put("page", currPage);
        this.put("limit", limit);

        String sidx = SQLFilter.sqlInject((String) params.get("sidx"));
        String order = SQLFilter.sqlInject((String) params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        this.page = new Page<>(currPage, limit);
        if (StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)) {
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }
    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }
}
