package com.nojava.test.spi_test;

import com.nojava.SPIInterface;

import java.util.ServiceLoader;

/**
 * java SPI学习
 * SPI（Service Provider Interface） JDK内置的服务发现机制
 * 将jar推到本地仓库mvn install:install-file -Dfile=D:\IdeaProjects\spitest\target\spitest-1.0-SNAPSHOT.jar  -DgroupId=com.nojava -DartifactId=spitest -Dversion=1.0 -Dpackaging=jar
 * 通过设置jar META-INF/services下一个全限定名路径的接口文件，接口文件里面存放的是实现类的路径
 * 项目引入该jar包 ServiceLoader会通过load方法读取接口class 会返回文件中实现类的集合，可以通过for循环
 */
public class SPITest {

    public static void main(String[] args) {

        ServiceLoader<SPIInterface> load = ServiceLoader.load(SPIInterface.class);
        for(SPIInterface s:load){
            s.operation(121,2);
        }

    }

}
