package com.xiaohongshu.fls.crm.keel.sample;


import com.xiaohongshu.fls.crm.keel.extension.Extension;
import com.xiaohongshu.fls.crm.keel.extension.ExtensionType;
import org.springframework.stereotype.Component;

@Component
@Extension(cond = "#id == 2 && #name == 'abc'", type = ExtensionType.SHARDED, order = 2)
public class WorldSampleReturnValueExt implements SampleReturnValueExtPt {
    @Override
    public String echo() {
        return "World";
    }
}
