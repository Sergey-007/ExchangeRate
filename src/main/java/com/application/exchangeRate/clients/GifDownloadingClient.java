package com.application.exchangeRate.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "media0", url = "${media}", qualifier = "media0")
public interface GifDownloadingClient {
    @RequestMapping(value = "{path}?{query}",method = RequestMethod.GET, consumes = MediaType.IMAGE_GIF_VALUE)
    byte[] downloadGif(@PathVariable String path, @PathVariable String query);
}
