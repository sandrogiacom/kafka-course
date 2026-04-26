package com.example.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

fun main() {
    val bootstrapServers = "localhost:29092"
    val topic = "user-events"
    val groupId = "user-events-consumer-group"

    val props = Properties()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
    props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(listOf(topic))

    println("Consumindo mensagens do tópico: $topic")
    println("Consumer Group: $groupId")
    println("Pressione Ctrl+C para parar...")

    try {
        while (true) {
            val records = consumer.poll(Duration.ofMillis(1000))
            for (record in records) {
                println("Topic: ${record.topic()}, Partition: ${record.partition()}, Offset: ${record.offset()}")
                println("Key: ${record.key()}, Value: ${record.value()}")
                println("---")
            }
        }
    } finally {
        consumer.close()
        println("Consumidor fechado.")
    }
}
