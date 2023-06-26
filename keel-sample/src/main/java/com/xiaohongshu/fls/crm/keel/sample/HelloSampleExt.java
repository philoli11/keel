package com.xiaohongshu.fls.crm.keel.sample;


import com.xiaohongshu.fls.crm.keel.extension.Extension;
import com.xiaohongshu.fls.crm.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#name == 'abc'", type = ExtensionType.SHARDED, order = 1)
public class HelloSampleExt implements SampleExtensionPoint {
    @Override
    public void echo() {
        System.out.println("Hello");
    }
}
