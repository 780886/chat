package com.wgq.chat.common.json;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JsonFactory {

    private static final String DEFAULT_PROVIDER = "com.wgq.chat.common.json.FastJsonExtensionJsonImpl";
    private volatile static Json json;

    public static Json getProvider() {
        if (json != null) {
            return json;
        }
        synchronized (JsonFactory.class) {
            if (json != null) {
                return json;
            }

            ServiceLoader<Json> loader = ServiceLoader.load(Json.class);
            Iterator<Json> it = loader.iterator();
            if (it.hasNext()) {
                json = it.next();
                return json;
            }

            try {
                Class<?> jsonClazz = Class.forName(DEFAULT_PROVIDER);
                json = (Json) jsonClazz.newInstance();
                return json;
            } catch (Exception x) {
                throw new RuntimeException(
                        "Provider " + DEFAULT_PROVIDER + " could not be instantiated: " + x,
                        x);
            }
        }
    }
}
