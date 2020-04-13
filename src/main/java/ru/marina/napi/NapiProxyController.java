package ru.marina.napi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NapiProxyController {
    private final NapiProxyService napiProxyService;

    @Autowired
    public NapiProxyController(final NapiProxyService napiProxyService) {
        this.napiProxyService = napiProxyService;
    }

    @GetMapping("/call")
    public @ResponseBody
    String call(@RequestParam("clazz") final String clazzName, final String request) {
        return napiProxyService.call(clazzName, request);
    }
}
