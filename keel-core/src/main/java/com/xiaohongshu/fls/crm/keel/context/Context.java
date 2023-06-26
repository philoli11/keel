package com.xiaohongshu.fls.crm.keel.context;

public interface Context {

    <T, R> Store<R> with(NamedFunction<T, R> namedFunction);

    interface Store<R> {
        R get();

        void set(R value);
    }
}
