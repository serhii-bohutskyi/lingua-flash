package com.bohutskyi.fx.linguaflash.translate.libretranslate;

import com.bohutskyi.fx.linguaflash.translate.TranslateClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class LibreTranslateClient implements TranslateClient {

    private WebClient webClient;

    private String translateUrl;

    public LibreTranslateClient(@Value("${translate.libretranslate.client.baseUrl}") String translateUrl) {
        this.translateUrl = translateUrl;
    }

    @PostConstruct
    private void init() {
        webClient = WebClient.builder()
                .baseUrl(translateUrl)
                             .build();
    }

    public String translate(String text, String targetLanguage) {

        TranslationRequest requestBody = new TranslationRequest(
                text,
                "en",
                targetLanguage,
                "text"
        );

        return Objects.requireNonNull(webClient.post()
                                               .contentType(MediaType.APPLICATION_JSON)
                                               .acceptCharset(StandardCharsets.UTF_8)
                                               .bodyValue(requestBody)
                                               .retrieve()
                                               .bodyToMono(TranslationResponse.class)
                                               .block())
                      .getTranslatedText();
    }

}
