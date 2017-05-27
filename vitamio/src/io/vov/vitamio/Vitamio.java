/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package io.vov.vitamio;

import android.annotation.SuppressLint;
import android.content.Context;

import io.vov.vitamio.utils.ContextUtils;


/**
 * Inspect this class before using any other Vitamio classes.
 * <p/>
 * Don't modify this class, or the full Vitamio library may be broken.
 */
public class Vitamio {
    private static String vitamioPackage;
    private static String vitamioLibraryPath;
    private static String vitamioDataPath;
    private static String browserlibraryPath;

    /**
     * Check if Vitamio is initialized at this device
     *
     * @param ctx Android Context
     * @return true if the Vitamio has been initialized.
     */
    @SuppressLint("NewApi")
    public static boolean isInitialized(Context ctx) {
        vitamioPackage = ctx.getPackageName();

        vitamioLibraryPath = ctx.getApplicationInfo().nativeLibraryDir + "/";
        vitamioDataPath = ContextUtils.getDataDir(ctx) + "lib/";
        browserlibraryPath = ctx.getApplicationContext().getDir("libs", Context.MODE_PRIVATE).getPath();

        return true;
    }

    public static String getVitamioPackage() {
        return vitamioPackage;
    }


    public static final String getLibraryPath() {
        return vitamioLibraryPath;
    }

    public static final String getDataPath() {
        return vitamioDataPath;
    }

    public static final String getBrowserLibraryPath() {
        return browserlibraryPath;
    }

}
