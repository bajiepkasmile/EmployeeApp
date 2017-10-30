package com.nodomain.employeeapp.di.modules;


import com.nodomain.employeeapp.data.datasources.cache.Cache;
import com.nodomain.employeeapp.data.datasources.local.LocalStorage;
import com.nodomain.employeeapp.data.datasources.local.LocalStorageImpl;
import com.nodomain.employeeapp.data.datasources.remote.RemoteStorage;
import com.nodomain.employeeapp.data.datasources.remote.RemoteStorageImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DataSourcesModule {

    @Singleton
    @Provides
    Cache provideCache() {
        return new Cache();
    }

    @Singleton
    @Provides
    LocalStorage provideLocalStorage(LocalStorageImpl storageImpl) {
        return storageImpl;
    }

    @Singleton
    @Provides
    RemoteStorage provideRemoteStorage(RemoteStorageImpl storageImpl) {
        return storageImpl;
    }
}
