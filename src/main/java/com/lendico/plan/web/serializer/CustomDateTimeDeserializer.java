package com.lendico.plan.web.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    private static final Logger logger = LoggerFactory.getLogger(CustomDateTimeDeserializer.class);

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String stringValue = jp.getValueAsString();
        if (StringUtils.isBlank(stringValue)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CustomDateTimeSerializer.DATE_FORMAT_PATTERN);
            return ZonedDateTime.parse(stringValue, formatter);
        } catch (IllegalArgumentException e) {
            logger.error("Unable to deserialize data: from " + stringValue);
            return null;
        }
    }
}
