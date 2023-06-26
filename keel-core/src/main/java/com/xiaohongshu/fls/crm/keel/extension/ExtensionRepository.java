package com.xiaohongshu.fls.crm.keel.extension;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExtensionRepository {
    private final Map<Class<? extends ExtensionPoint>, List<ExtensionPoint>> extensionRepo = new HashMap<>();

    private final Map<Class<? extends ExtensionPoint>, Extension> extensionAnnoRepo = new HashMap<>();

    public Map<Class<? extends ExtensionPoint>, List<ExtensionPoint>> getExtensionRepo() {
        return extensionRepo;
    }

    public Map<Class<? extends ExtensionPoint>, Extension> getExtensionAnnoRepo() {
        return extensionAnnoRepo;
    }
}
