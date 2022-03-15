package gg.scala.messaging

import gg.scala.aware.uri.WrappedAwareUri

/**
 * @author GrowlyX
 * @since 3/14/2022
 */
val config = MessengerConfig

val redis = WrappedAwareUri(
    config.get("address"),
    config.get("port").toInt(),
)
