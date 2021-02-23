package com.slzhkj.smartworksite.server.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * <h1>The advice for bean injection</h1>
 *
 * @author Yuezejian
 * @date 2020年 11月17日 14:35:19
 */
@Component
public class BeanWiredAdvice implements ApplicationContextAware, Ordered {

    private static volatile ApplicationContext applicationContext;

    /**
     * <h2>
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     * </h2>
     *
     * @param context the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if ( applicationContext == null ) {
            synchronized (ApplicationContext.class) {
                if ( applicationContext == null ) {
                    applicationContext = context;
                }
            }
        }
    }

    /**
     * <h2>
     * After getting the application context object instance,
     * you can manually obtain the bean injection instance object
     * </h2>
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * <h2>Makes the current class load with a higher priority</h2>
     * @return
     */
    @Override
    public int getOrder() {
        return 3;
    }
}
