package gg.scala.messaging

import java.util.*

/**
 * @author GrowlyX
 * @since 3/14/2022
 */
object MessengerConfig
{
    private val properties = Properties()
        .apply {
            val resource = this::class.java
                .getResourceAsStream(
                    "redis.properties"
                )

            load(resource)
        }

    fun get(key: String): String =
        properties.getProperty(key)!!
}
