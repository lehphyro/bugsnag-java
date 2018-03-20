package com.bugsnag.logback;

import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class BugsnagAppenderTest {

    public static final String USER_ID_PROPERTY = "user-id";
    public static final String USER_ID_VALUE = "123";

    public static final String USER_NAME_PROPERTY = "username";
    public static final String USER_NAME_VALUE = "test-user";

	public static final String USER_EMAIL_PROPERTY = "user-email";
	public static final String USER_EMAIL_VALUE = "test@test.com";

	public static final String REQUEST_PROPERTY = "property2";
	public static final String REQUEST_VALUE = "value2";

	@Test
    public void testConfigurationIsPassedThrough() {
        MDC.put(USER_ID_PROPERTY, USER_ID_VALUE);
        MDC.put(USER_NAME_PROPERTY, USER_NAME_VALUE);
		MDC.put(USER_EMAIL_PROPERTY, USER_EMAIL_VALUE);
		MDC.put(REQUEST_PROPERTY, REQUEST_VALUE);
        LoggerFactory.getLogger(getClass()).info("Test message");
        // No assertions here, checks are done in TestCallback
    }

    private void configureLogback(String configFileName) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        try {
            configurator.doConfigure(getClass().getResource(configFileName));
        } catch (JoranException e) {
            // Error messages are asserted in the tests
        }
    }
}