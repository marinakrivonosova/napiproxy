package ru.marina.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.vip.waltz.base.DomDecimal;

import java.io.IOException;

public class CustomSerializerDomBigDecimal extends StdSerializer<DomDecimal> {
    protected CustomSerializerDomBigDecimal(final Class<DomDecimal> t) {
        super(t);
    }
    public CustomSerializerDomBigDecimal() {
        this(null);
    }

    @Override
    public void serialize(final DomDecimal domDecimal, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(domDecimal.getDecimal());
    }
}
