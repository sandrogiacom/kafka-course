package com.example.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main() {
    val bootstrapServers = "localhost:29092"
    val topic = "user-events"

    val props = Properties()
    props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
    props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
    props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name

    val producer = KafkaProducer<String, String>(props)

    val record = ProducerRecord<String, String>(topic, "test-key", "Hello from Kotlin Producer!")

    producer.send(record) { metadata, exception ->
        if (exception == null) {
            println("Message sent successfully: topic=${metadata.topic()}, partition=${metadata.partition()}, offset=${metadata.offset()}")
        } else {
            exception.printStackTrace()
        }
    }

    producer.flush()
    producer.close()
}
