package ru.marina.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vip.waltz.base.DomLong;

import java.io.IOException;

public class CustomSerializerDomLong extends StdSerializer<DomLong> {
    protected CustomSerializerDomLong(final Class<DomLong> t) {
        super(t);
    }

    public CustomSerializerDomLong() {
        this(null);
    }

    @Override
    public void serialize(final DomLong domLong, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(domLong.getDbValue());
    }
}
