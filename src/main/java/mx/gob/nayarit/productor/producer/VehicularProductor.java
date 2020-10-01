package mx.gob.nayarit.productor.producer;

import mx.gob.nayarit.productor.model.Vehicular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class VehicularProductor {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicularProductor.class);
    private final String vehicularTopic = "vehicular-topic";

    @Autowired
    private KafkaTemplate<String, Vehicular> kafkaTemplate;

    public void send(Vehicular vehicular){
        Message<Vehicular> message =
                      MessageBuilder.withPayload(vehicular)
                      .setHeader(KafkaHeaders.TOPIC, vehicularTopic)
                      .build();
        kafkaTemplate.send(message);
        LOGGER.info("El mensaje se envio exitosamente!!!");
    }
}
