package com.nojava.routingdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源选择‘
 * 主要继承AbstractRoutingDataSource 从写determineCurrentLookupKey方法返回设置在threadlocal数据源
 */
public class MultipleRoutingDataSource extends AbstractRoutingDataSource {

    /**
     *
     * @return threadlocal数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSouce();
    }
}
