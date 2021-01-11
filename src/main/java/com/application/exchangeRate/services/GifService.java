package com.application.exchangeRate.services;

import com.application.exchangeRate.clients.GifDownloadingClient;
import com.application.exchangeRate.clients.GifClient;
import com.application.exchangeRate.models.GifObject;
import com.application.exchangeRate.models.Gif;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Service
public class GifService {
    @Value("${gifApiKey}")
    private String api_key;

    private GifClient gifClient;
    private ApplicationContext applicationContext;

    public GifService(GifClient gifClient, ApplicationContext applicationContext) {
        this.gifClient = gifClient;
        this.applicationContext = applicationContext;
    }
    public byte[] randomGif(boolean ifRubleExchangeRateGreaterThanYesterday) throws IOException {
        String Word = ifRubleExchangeRateGreaterThanYesterday ? "rich" : "broke";
        Gif dataGif = gifClient.searchGif(api_key, Word);
        GifObject[] gif = dataGif.getData();
        int random = (int)(Math.random() * gif.length);
        Map<String, String> finalGif = gif[random].getImages().getOriginal();

        URL url = new URL(finalGif.get("url"));
        String mediaDomainName = "media0";
        String query = url.getQuery();
        String path = url.getPath();
        GifDownloadingClient Client = (GifDownloadingClient) applicationContext.getBean(mediaDomainName);
        return Client.downloadGif(path, query);
    }
}
