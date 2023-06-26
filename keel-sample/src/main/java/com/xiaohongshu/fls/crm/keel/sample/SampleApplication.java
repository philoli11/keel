package com.xiaohongshu.fls.crm.keel.sample;

import com.xiaohongshu.fls.crm.keel.context.KeelContext;
import com.xiaohongshu.fls.crm.keel.extension.ExtensionExecutor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.xiaohongshu.fls.crm.keel.sample")
public class SampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder().sources(SampleApplication.class).run(args);

        ExtensionExecutor extensionExecutor = applicationContext.getBean(ExtensionExecutor.class);

        KeelContext keelContext = KeelContext.create();
        keelContext.with(SampleNamedDefine::getId).set(2L);
        keelContext.with(SampleNamedDefine::getName).set("abc");

        extensionExecutor.executeVoid(SampleExtensionPoint.class, keelContext, SampleExtensionPoint::echo);

        keelContext.with(SampleNamedDefine::getId).set(1L);
        keelContext.with(SampleNamedDefine::getName).set("abc");

        String result = extensionExecutor.execute(SampleReturnValueExtPt.class, keelContext, SampleReturnValueExtPt::echo);
        System.out.println(result);
    }
}
