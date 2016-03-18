package com.lukaszbyjos.popularmovies;

import com.facebook.stetho.Stetho;

/**
 * Created by lbyjos on 2015-12-07 on 11:25.
 */
public class DebugApplicationClass extends ApplicationClass {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());


    }
}
