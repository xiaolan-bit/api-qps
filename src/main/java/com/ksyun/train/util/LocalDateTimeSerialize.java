package com.ksyun.train.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerialize {

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String timeStr = value == null ? null : DateUtil.format(value);
            gen.writeString(timeStr);
        }
    }

    public static class LocalDateTimeJsonDeserialize extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            ObjectCodec oc = p.getCodec();
            JsonNode node = oc.readTree(p);
            if (node.isNull()) {
                return null;
            }
            return DateUtil.parse(node.asText());
        }
    }
}