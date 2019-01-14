package com.yp.demo.configtest;

import org.junit.Test;
import com.yp.demo.configuration.Log4J2PropertiesConf;

public class Log4J2PropertiesConfTest {
	@Test
    public void testPerformSomeTask() throws Exception {
        Log4J2PropertiesConf log4J2PropertiesConf=new Log4J2PropertiesConf();
        log4J2PropertiesConf.performSomeTask();
    }
}
