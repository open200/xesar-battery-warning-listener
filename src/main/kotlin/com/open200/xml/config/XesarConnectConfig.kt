package com.open200.xml.config

import com.open200.xesar.connect.Config
import com.open200.xesar.connect.XesarConnect
import kotlinx.coroutines.runBlocking
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Path
import java.security.Security

@Configuration
class XesarConnectConfig {

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    @Bean
    fun config(@Value("\${xesarconnect.credentials-zip-path}") zipPath: Path): Config {
        return Config.configureFromZip(configurationZipFile = zipPath, logoutOnClose = false)
    }

    @Bean
    fun xesarConnect(config: Config): XesarConnect {
        return runBlocking {
            XesarConnect.connectAndLoginAsync(config, closeUponCoroutineCompletion = false).await()
        }
    }
}
