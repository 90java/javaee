package com.nojava.test.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源选择
 */
public class MultipleRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSouce();
    }
}
