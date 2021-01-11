package com.application.exchangeRate.clients;

import com.application.exchangeRate.models.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif", url = "${searchingGifPath}" )
public interface GifClient {
    @RequestMapping(value = "/v1/gifs/search", method = RequestMethod.GET, params = {"api_key", "q"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    Gif searchGif(@RequestParam(value = "api_key") String api_key, @RequestParam(value = "q") String Word);
}
