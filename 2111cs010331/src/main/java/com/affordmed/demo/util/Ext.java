package com.affordmed.demo.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;

/**
 * @author Udhaya
 * Created on 02-03-2023
 */

public class StringExtensions {
    public static URI toURL(String str) {
        try {
            return new URL(str).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return null;
        }
    }
}

