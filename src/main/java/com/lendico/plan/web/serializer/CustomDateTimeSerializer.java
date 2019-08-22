package com.lendico.plan.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CustomDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssX";

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        if (value == null) {
            // do nothing
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
            String formatDateTime = value.format(formatter);
            gen.writeObject(formatDateTime);
        }
    }
}
