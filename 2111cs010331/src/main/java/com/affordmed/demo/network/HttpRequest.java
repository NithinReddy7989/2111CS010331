package com.affordmed.demo.network;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.android.Android;
import io.ktor.client.plugins.HttpTimeout;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.withContext;

public class HttpRequest {
    private final String url;

    public HttpRequest(String url) {
        this.url = url;
    }

    private static HttpInterface createClient() {
        return new HttpInterfaceImpl(new HttpClient(Android.Companion.create()) {{
            setFollowRedirects(true);
            install(HttpTimeout.class, config -> config.setRequestTimeoutMillis(400));
        }});
    }

    /**
     * Fetches plain text for given url
     *
     * @return Text format of entire webpage for given url
     */
    public suspend String getResponse() {
        return withContext(Dispatchers.IO, () -> createClient().getData(url));
    }
}

