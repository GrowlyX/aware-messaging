package gg.scala.messaging

import gg.scala.aware.message.AwareMessage
import gg.scala.aware.thread.AwareThreadContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author GrowlyX
 * @since 3/14/2022
 */
object MessengerPoller
{
    val format = SimpleDateFormat(
        "hh:mm aa"
    )

    private lateinit var username: String

    fun configure()
    {
        this.username = listOf(
            "Please enter a username!",
            " This will be shown to all clients!",
            ""
        ).response()

        println("Successfully connected! Start chatting.")

        Messenger.connected = true

        while (true)
        {
            val message = reader.readLine()

            AwareMessage.of(
                "message",
                Messenger.aware,
                "content" to message,
                "username" to this.username,
                "timestamp" to Date()
            ).publish(
                AwareThreadContext.SYNC
            )
        }
    }

    private val reader = BufferedReader(
        InputStreamReader(System.`in`)
    )

    private fun List<String>.response(): String
    {
        forEach { println(it) }
        return reader.readLine()
    }
}
