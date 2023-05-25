package src.mq.kafka.simple;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * 类说明：消费者入门
 */
public class HelloKafkaConsumer {

    public static void main(String[] args) {
        // 设置属性
        Properties properties = new Properties();
        // 指定连接的kafka服务器的地址
        properties.put("bootstrap.servers","127.0.0.1:9092");
        // 设置String的反序列化
        properties.put("key.deserializer", StringDeserializer.class);
        properties.put("value.deserializer", StringDeserializer.class);
        properties.put("group.id","ConsumerOffsets11");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,1);


        // 构建kafka消费者对象
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        try {
            consumer.subscribe(Collections.singletonList("msb"));
//            final Map<TopicPartition, OffsetAndMetadata> offsets, final Duration timeout
//            consumer.commitSync();

            // 调用消费者拉取消息
            while(true){
                // 设置1秒的超时时间
                ConsumerRecords<String, String> records= consumer.poll(Duration.ofSeconds(1));
                for(ConsumerRecord<String, String> record:records){

                    String key = record.key();
                    String value = record.value();
                    long offset = record.offset();
                    if (offset == 5 || offset == 99) {

                    } else {
                        consumer.commitSync();
                    }
                    System.out.println("接收到消息: key = " + key + ", value = " + value + ", offset = " + offset);
                }
            }
        } finally {
            // 释放连接
            consumer.close();

        }

    }




}
