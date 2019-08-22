package com.lendico.plan.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;
import java.time.ZonedDateTime;

public class CustomDateTimeSerializerTest {

    private CustomDateTimeSerializer serializer = new CustomDateTimeSerializer();

    @Mock
    private JsonGenerator jsonGenerator;

    @Captor
    private ArgumentCaptor<String> captor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSerializeToString() throws IOException {

        ZonedDateTime time = ZonedDateTime.parse("2007-12-03T10:15:30+01:00");

        serializer.serialize(time, jsonGenerator, null);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObject(captor.capture());

        String capturedStringValue = captor.getValue();

        Assert.assertEquals("2007-12-03T10:15:30+01", capturedStringValue);
    }
}
