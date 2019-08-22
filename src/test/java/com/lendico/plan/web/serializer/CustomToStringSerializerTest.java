package com.lendico.plan.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.io.IOException;

public class CustomToStringSerializerTest {

    private CustomToStringSerializer serializer = new CustomToStringSerializer();

    @Mock
    private JsonGenerator jsonGenerator;

    @Captor
    private ArgumentCaptor<String> captor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldIgnoreNull() throws IOException {
        serializer.serialize(null, jsonGenerator, null);
        Mockito.verify(jsonGenerator, Mockito.times(0)).writeObject(captor.capture());
    }

    @Test
    public void shouldSerializeToString() throws IOException {

        serializer.serialize(100.0d, jsonGenerator, null);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeObject(captor.capture());

        String capturedValue = captor.getValue();

        Assert.assertEquals("100.0", capturedValue);
    }
}
