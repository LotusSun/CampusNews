/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package io.vov.vitamio.utils;

import java.util.MissingFormatArgumentException;

import io.vov.vitamio.BuildConfig;

public class Log {
    public static final String TAG = "Vitamio[Player]";

    public static void i(String msg, Object... args) {
        try {
            if (BuildConfig.DEBUG)
                android.util.Log.i(TAG, String.format(msg, args));
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "vitamio.Log", e);
            android.util.Log.i(TAG, msg);
        }
    }

    public static void d(String msg, Object... args) {
        try {
            if (BuildConfig.DEBUG)
                android.util.Log.d(TAG, String.format(msg, args));
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "vitamio.Log", e);
            android.util.Log.d(TAG, msg);
        }
    }

    public static void e(String msg, Object... args) {
        try {
            android.util.Log.e(TAG, String.format(msg, args));
        } catch (MissingFormatArgumentException e) {
            android.util.Log.e(TAG, "vitamio.Log", e);
            android.util.Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable t) {
        android.util.Log.e(TAG, msg, t);
    }
}
