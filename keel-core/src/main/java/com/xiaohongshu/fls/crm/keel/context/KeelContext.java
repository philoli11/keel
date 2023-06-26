package com.xiaohongshu.fls.crm.keel.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class KeelContext implements Context {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeelContext.class);

    private final Map<String, Object> contextMap;

    private KeelContext() {
        contextMap = new HashMap<>();
    }

    public static KeelContext create() {
        return new KeelContext();
    }

    private <T, R> String genKey(NamedFunction<T, R> namedFunction) {
        return namedFunction.getFieldName();
    }

    @Override
    public <T, R> Store<R> with(NamedFunction<T, R> namedFunction) {
        return new KeelContextStore<>(namedFunction);
    }

    public Map<String, Object> getContextMap() {
        return contextMap;
    }

    @Override
    public String toString() {
        return contextMap.toString();
    }

    private class KeelContextStore<T, R> implements Store<R> {

        private final NamedFunction<T, R> namedFunction;

        private KeelContextStore(NamedFunction<T, R> namedFunction) {
            this.namedFunction = namedFunction;
        }

        @SuppressWarnings("unchecked")
        @Override
        public R get() {
            return (R) contextMap.get(genKey(namedFunction));
        }

        @Override
        public void set(R value) {
            String key = genKey(namedFunction);
            if (contextMap.containsKey(key)) {
                LOGGER.warn("context key already exist, key: {}, oldVal: {}, newVal: {}", key, contextMap.get(key), value);
            }
            contextMap.put(key, value);
        }
    }
}
