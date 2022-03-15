package gg.scala.messaging

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import gg.scala.aware.Aware
import gg.scala.aware.AwareBuilder
import gg.scala.aware.AwareHub
import gg.scala.aware.codec.codecs.interpretation.AwareMessageCodec
import gg.scala.aware.message.AwareMessage
import gg.scala.messaging.MessengerPoller.format
import java.util.*

/**
 * @author GrowlyX
 * @since 3/14/2022
 */
object Messenger
{
    private val gson: Gson = GsonBuilder()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create()

    lateinit var aware: Aware<AwareMessage>

    var connected = false

    @JvmStatic
    fun main(args: Array<String>)
    {
        AwareHub.configure(redis) { gson }

        aware = AwareBuilder
            .of<AwareMessage>("messenger")
            .codec(AwareMessageCodec)
            .build()

        aware.listen("message") {
            if (!connected)
                return@listen

            val timestamp =
                retrieve<Date>("timestamp")

            val username =
                retrieve<String>("username")

            val formatted = format
                .format(timestamp)

            val content =
                retrieve<String>("content")

            if (content.isBlank())
                return@listen

            println("$username - $formatted: $content")
        }

        aware.connect().toCompletableFuture().join()

        MessengerPoller.configure()
    }
}
