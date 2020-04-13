package ru.marina.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vip.waltz.base.DomShort;

import java.io.IOException;

public class CustomSerializerDomShort extends StdSerializer<DomShort> {
    protected CustomSerializerDomShort(final Class<DomShort> t) {
        super(t);
    }

    public CustomSerializerDomShort() {
        this(null);
    }

    @Override
    public void serialize(final DomShort domShort, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(domShort.getDbValue());
    }
}
