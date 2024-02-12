/**
 * File: MainActivity.java
 * Created at: February 2, 2024
 * Author: muhammad harris fadilah
 */
package com.luzi.play21.helper;

import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collection;

public final class Strings {

    public static boolean containsNullOrEmpty(String... strings) {
        for (String str : strings) {
            if (str == null || str.isEmpty() || str.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public static boolean allValid(String... strings) {
        for (String str : strings) {
            if (containsNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }
    public static String collectionToString(Collection collection) {
        if (collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : collection) {
            String stringObj = getStringRepresentation(obj);
            if (allValid(stringObj)) {
                sb.append(", ");
                sb.append(stringObj);
            }
        }
        return sb.toString();
    }

    public static String getStringRepresentation(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof InputStream) {
            return getStringFromInputStream((InputStream) obj);
        } else if (obj instanceof Reader) {
            return getStringFromReader((Reader) obj);
        } else if (obj instanceof Object[]) {
            return collectionToString(Arrays.asList((Object[]) obj));
        } else if (obj instanceof Collection) {
            return collectionToString((Collection) obj);
        } else {
            return obj.toString();
        }
    }

    private static String getStringFromInputStream(InputStream inputStream) {
        return "";
    }

    private static String getStringFromReader(Reader reader) {
        return "";
    }
}
