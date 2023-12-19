package com.alad1nks.dubovozkibackend

import com.alad1nks.dubovozkibackend.storage.Storage
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import java.util.prefs.Preferences

@Configuration
class SingletonBeanConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun storage(): Storage {
        return Storage(Preferences.userRoot().node("dubovozki"))
    }
}