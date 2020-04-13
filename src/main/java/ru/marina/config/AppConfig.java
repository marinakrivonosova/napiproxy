package ru.marina.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vip.waltz.base.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final SimpleModule moduleDecimal = new SimpleModule("CustomSerializerDomBigDecimal");
        moduleDecimal.addSerializer(DomDecimal.class, new CustomSerializerDomBigDecimal());
        objectMapper.registerModule(moduleDecimal);

        final SimpleModule moduleInt = new SimpleModule("CustomSerializerDomInt");
        moduleInt.addSerializer(DomInt.class, new CustomSerializerDomInt());
        objectMapper.registerModule(moduleInt);

        final SimpleModule moduleLong = new SimpleModule("CustomSerializerDomLong");
        moduleLong.addSerializer(DomLong.class, new CustomSerializerDomLong());
        objectMapper.registerModule(moduleLong);

        final SimpleModule moduleShort = new SimpleModule("CustomSerializerDomShort");
        moduleShort.addSerializer(DomShort.class, new CustomSerializerDomShort());
        objectMapper.registerModule(moduleShort);

        final SimpleModule moduleString = new SimpleModule("CustomSerializerDomString");
        moduleString.addSerializer(DomString.class, new CustomSerializerDomString());
        objectMapper.registerModule(moduleString);
        return objectMapper;
    }
}
