package com.nojava.routingdatasource;

/**
 * 根据当前线程选择数据源
 */
public class HandlerDataSource {


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    /**
     * 提供aop设置当前的线程的数据源信息
     * @param dataSouce
     */
    public static void setDataSouce(String dataSouce){
        System.out.println(Thread.currentThread().getId()+"-setDataSouce:"+dataSouce);
        threadLocal.set(dataSouce);
    }

    /**
     * 获取当前线程的数据源信息
     * @return
     */
    public static String getDataSouce(){
        System.out.println(Thread.currentThread().getId()+"-getDataSouce:"+threadLocal.get());
        return threadLocal.get();
    }

    /**
     * 清除当前线程信息
     */
    public static void clearDataSource(){
        System.out.println(Thread.currentThread().getId()+"-clearDataSource");
        threadLocal.remove();
    }



}
