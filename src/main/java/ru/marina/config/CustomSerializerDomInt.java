package ru.marina.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vip.waltz.base.DomInt;

import java.io.IOException;

public class CustomSerializerDomInt extends StdSerializer<DomInt> {
    protected CustomSerializerDomInt(final Class<DomInt> t) {
        super(t);
    }

    public CustomSerializerDomInt() {
        this(null);
    }

    @Override
    public void serialize(final DomInt domInt, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(domInt.getDbValue());
    }

}
