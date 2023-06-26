package io.philo.framework.keel.extension;

import io.philo.framework.keel.context.KeelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Component
public class ExtensionExecutor extends AbstractComponentExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionExecutor.class);

    @Resource
    private ExtensionRepository extensionRepository;

    private final ExpressionParser parser = new SpelExpressionParser();

    @Override
    protected <C> List<C> locateComponent(Class<C> targetClz, KeelContext context) {
        List<C> locatedExtension = locateExtension(targetClz, context);
        LOGGER.info("[Located Extension]: " + locatedExtension);
        return locatedExtension;
    }

    protected <Ext> List<Ext> locateExtension(Class<Ext> targetClz, KeelContext context) {
        checkNull(context);

        Ext extension;
        LOGGER.debug("context in locateExtension is : " + context);

        List<Ext> extensionPointList = (List<Ext>) extensionRepository.getExtensionRepo().get(targetClz);

        List<Ext> locateExtensions = new ArrayList<>();
        List<Extension> locateExtensionAnnos = new ArrayList<>();

        if (Objects.nonNull(extensionPointList)) {
            for (Ext ext : extensionPointList) {
                Extension extensionAnno = extensionRepository.getExtensionAnnoRepo().get(ext.getClass());
                if (isMatched(context, extensionAnno.cond())) {
                    locateExtensions.add(ext);
                    locateExtensionAnnos.add(extensionAnno);
                }
            }
        }

        if (locateExtensions.isEmpty()) {
            String errMessage = "Can not find extension with ExtensionPoint: " +
                    targetClz + ", context:" + context;
            throw new RuntimeException(errMessage);
        }

        checkExtension(locateExtensions, locateExtensionAnnos);

        sort(locateExtensions);

        return locateExtensions;
    }

    private <Ext> void sort(List<Ext> locateExtensions) {
        locateExtensions.sort(Comparator.comparingInt(o -> AnnotationUtils.findAnnotation(o.getClass(), Extension.class).order()));
    }

    private <Ext> void checkExtension(List<Ext> extensions, List<Extension> extensionAnnos) {
        Extension extensionAnno = extensionAnnos.get(0);

        ExtensionType extensionType = extensionAnno.type();

        if (extensionType == ExtensionType.EXCLUSIVE && extensions.size() > 1) {
            String errorMessage = "Can not support multi EXCLUSIVE extension at the same time: " + extensions;
            throw new RuntimeException(errorMessage);
        }

        for (Extension anno : extensionAnnos) {
            if (anno.type() != extensionType) {
                String errorMessage = "Can not support EXCLUSIVE and SHARDED extension at the same time: " + extensions;
                throw new RuntimeException(errorMessage);
            }
        }
    }

    private boolean isMatched(KeelContext context, String cond) {
        Expression expression = parser.parseExpression(cond);
        EvaluationContext evaluationContext = mapEvaluationContext(context);
        return Boolean.TRUE.equals(expression.getValue(evaluationContext, Boolean.class));
    }

    private EvaluationContext mapEvaluationContext(KeelContext context) {
        SimpleEvaluationContext simpleEvaluationContext = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        for (Map.Entry<String, Object> entry : context.getContextMap().entrySet()) {
            simpleEvaluationContext.setVariable(entry.getKey(), entry.getValue());
        }
        return simpleEvaluationContext;
    }

    private void checkNull(KeelContext context) {
        if (context == null) {
            throw new IllegalArgumentException("KeelContext can not be null for extension");
        }
    }
}
