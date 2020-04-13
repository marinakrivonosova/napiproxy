package ru.marina.napi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.waltz.ens.domain.*;
import com.vip.waltz.napi.ws.api.IAuthenticator;
import com.vip.waltz.napi.ws.api.ISubscriberAccumulators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NapiProxyServiceTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NapiProxyService napiProxyService;

    @Test
    void call() throws JsonProcessingException {
//       final Authenticator authenticator = new Authenticator();
//       //authenticator.setSESSION_ID(0);
//       authenticator.setPASSWORD("empty");
//       authenticator.setUSER_ID(0);
//       authenticator.setWSNADDR("http://localhost:");

        final IAuthenticator.AuthenticatorIn request = new IAuthenticator.AuthenticatorIn();
        request.wsnaddr = WsnAddr.valueOf("http://localhost:9999");
        request.user_id = OperId.valueOf(2);
        request.getTokenInd = YesNoInd.valueOf("Y");
        request.password = AccessPsw.valueOf("empty");

        final String json = objectMapper.writeValueAsString(request);

        final String call = napiProxyService.call("com.vip.ensemble.napi.Authenticator", json);


//        {
//            "user_id": 1,
//                "password": "empty",
//                "wsnaddr": "empty",
//                "getTokenInd": "Y"
//        }
        assertEquals("{\"state\":\"00000\",\"session_id\":0,\"user_id\":2,\"total_count\":1,\"token\":\"Y\"}", call);
    }

    @Test
    void call2() throws JsonProcessingException {
        final ISubscriberAccumulators.SubscriberAccumulatorsIn subscriberAccumulatorsIn = new ISubscriberAccumulators.SubscriberAccumulatorsIn();
        subscriberAccumulatorsIn.customer_ban = Ban.valueOf("000");
        subscriberAccumulatorsIn.session_id = EntSeqNo.valueOf(1);
        subscriberAccumulatorsIn.source_id = SeqVal.valueOf("111");
        subscriberAccumulatorsIn.subno = SubNo.valueOf("000");

        final String json = objectMapper.writeValueAsString(subscriberAccumulatorsIn);
        final String call = napiProxyService.call("com.vip.ensemble.napi.SubscriberAccumulators", json);

        assertEquals("{\"rowcount\":1," +
                "\"cycle_code\":[111]," +
                "\"cycle_month\":[2]," +
                "\"cycle_year\":[2]," +
                "\"customer_id\":[1]," +
                "\"subscriber_no\":[\"1\"]," +
                "\"acc_type\":[\"1\"]," +
                "\"acc_key\":[\"111\"]," +
                "\"ben\":[123]," +
                "\"bill_seq_no\":[20]," +
                "\"soc\":[\"20\"]," +
                "\"soc_seq_no\":[0]," +
                "\"effective_date\":[\"1010\"]," +
                "\"expiration_date\":[\"10\"]," +
                "\"target_ban\":[100]," +
                "\"acc_value\":[\"1\"]," +
                "\"acc_aux\":[\"0\"]," +
                "\"uacc_name\":[\"0\"]," +
                "\"description\":[\"0\"]," +
                "\"state\":\"ER003\"}", call);

    }
}