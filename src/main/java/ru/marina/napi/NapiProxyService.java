package ru.marina.napi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.ensemble.napi.Authenticator;
import com.vip.ensemble.napi.CloseSession;
import com.vip.ensemble.napi.NAPIGeneric;
import com.vip.ensemble.napi.WsBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marina.util.PropertyUtils;

@Service
public class NapiProxyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NapiProxyService.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public NapiProxyService(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String call(final String clazzName, final String request) {
        NAPIGeneric.init("http://localhost:9999");
        try {
            final Class<WsBase> clazz = (Class<WsBase>) Class.forName(clazzName);
            final WsBase serviceCall;
            final Object input = objectMapper.readValue(request, clazz.getDeclaredField("in").getType());

            if (clazz.equals(Authenticator.class) || clazz.equals(CloseSession.class)) {
                serviceCall = clazz.getConstructor().newInstance();
            } else {
                // sessionId param of constructor used to set up session_id of WsBase request
                // and since we are going to replace request of WsBase anyway, session_id will be overridden
                // hence sessionId passed to constructor has no impact on anything and we can pass any value
                serviceCall = clazz.getConstructor(int.class).newInstance(0);
            }

            PropertyUtils.setPrivateProperty(serviceCall, "in", input);

            serviceCall.callService();

            //TODO if-else needed?

//                response = objectMapper.writeValueAsString(PropertyUtils.getPrivateProperty(PropertyUtils.getPrivateProperty(serviceCall, "csNapiSes"), "out"));
//            } else {
//                response = objectMapper.writeValueAsString(PropertyUtils.getPrivateProperty(serviceCall, "out"));
//            }
            return objectMapper.writeValueAsString(PropertyUtils.getPrivateProperty(serviceCall, "out"));
        } catch (final Exception e) { //TODO Jolt Exception was called in original call() method and error message was set into result object
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
