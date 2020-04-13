package ru.marina.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vip.waltz.base.DomString;

import java.io.IOException;

public class CustomSerializerDomString extends StdSerializer<DomString> {
    protected CustomSerializerDomString(final Class<DomString> t) {
        super(t);
    }

    public CustomSerializerDomString() {
        this(null);
    }

    @Override
    public void serialize(final DomString domString, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(domString.getValue());
    }
}
