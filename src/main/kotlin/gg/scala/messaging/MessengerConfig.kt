package gg.scala.messaging

import java.util.*

/**
 * @author GrowlyX
 * @since 3/14/2022
 */
object MessengerConfig
{
    private val properties by lazy {
        val props = Properties()
        props.load(
            this::class.java.classLoader
                .getResourceAsStream("redis.properties")
        )

        return@lazy props
    }

    fun get(key: String): String =
        properties.getProperty(key)!!
}
