package com.lendico.plan.web.serializer;

import com.fasterxml.jackson.core.JsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.ZonedDateTime;

public class CustomDateTimeDeserializerTest {

    private CustomDateTimeDeserializer deserializer = new CustomDateTimeDeserializer();

    @Mock
    private JsonParser jsonParser;

    @Before
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(jsonParser.getValueAsString()).thenReturn("2007-12-03T10:15:30+01");
    }

    @Test
    public void shouldSerializeToString() throws IOException {
        ZonedDateTime parsedTime = deserializer.deserialize(jsonParser, null);
        Assert.assertEquals(ZonedDateTime.parse("2007-12-03T10:15:30+01:00"), parsedTime);
    }
}
