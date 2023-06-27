package io.philo.framework.keel.register;


import io.philo.framework.keel.extension.Extension;
import io.philo.framework.keel.extension.ExtensionPoint;
import io.philo.framework.keel.extension.ExtensionRepository;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ExtensionRegister {

    @Resource
    private ExtensionRepository extensionRepository;

    @SuppressWarnings("unchecked")
    public void doRegistration(ExtensionPoint extensionObject) {
        Class<?> extensionClz = extensionObject.getClass();
        if (AopUtils.isAopProxy(extensionObject)) {
            extensionClz = ClassUtils.getUserClass(extensionObject);
        }
        Extension extensionAnn = AnnotationUtils.findAnnotation(extensionClz, Extension.class);

        Extension preVal = extensionRepository.getExtensionAnnoRepo().put((Class<? extends ExtensionPoint>) extensionClz, extensionAnn);

        if (preVal != null) {
            String errMessage = "Duplicate registration is not allowed for : " + extensionClz;
            throw new RuntimeException(errMessage);
        }

        Class<?>[] allInterfacesForClz = ClassUtils.getAllInterfacesForClass(extensionClz);
        Class<?> extensionInterfaceClz = null;
        for (Class<?> aClass : allInterfacesForClz) {
            if (ExtensionPoint.class.isAssignableFrom(aClass)) {
                extensionInterfaceClz = aClass;
            }
        }

        if (Objects.isNull(extensionInterfaceClz)) {
            String errMessage = "Can not find extension interface : " + extensionClz;
            throw new RuntimeException(errMessage);
        }

        List<ExtensionPoint> extensionPointList = extensionRepository.getExtensionRepo().get(extensionInterfaceClz);

        if (Objects.isNull(extensionPointList)) {
            extensionPointList = new ArrayList<>();
            extensionRepository.getExtensionRepo().put((Class<? extends ExtensionPoint>) extensionInterfaceClz, extensionPointList);
        }
        extensionPointList.add(extensionObject);
    }
}
