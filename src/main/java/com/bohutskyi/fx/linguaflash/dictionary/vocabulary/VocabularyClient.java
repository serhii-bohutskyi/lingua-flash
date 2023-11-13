package com.bohutskyi.fx.linguaflash.dictionary.vocabulary;

import com.bohutskyi.fx.linguaflash.dictionary.DictionaryClient;
import com.bohutskyi.fx.linguaflash.dictionary.DictionaryWord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class VocabularyClient implements DictionaryClient {

    private final VocabularyParser vocabularyParser;

    private final VocabularyProperties vocabularyProperties;

    private final ObjectMapper objectMapper;

    private WebClient webClient;

    public VocabularyClient(
            VocabularyParser vocabularyParser,
            VocabularyProperties vocabularyProperties,
            ObjectMapper objectMapper
    ) {
        this.vocabularyParser = vocabularyParser;
        this.vocabularyProperties = vocabularyProperties;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init() {
        webClient = WebClient.builder().baseUrl(vocabularyProperties.getBaseUrl()).build();
    }

    @Override
    public Mono<DictionaryWord> randomWord() {
        return webClient.get()
                        .uri(uriBuilder -> uriBuilder.path(vocabularyProperties.getRandomPath()).build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .map(wordJson -> {
                            try {
                                return objectMapper.reader().readTree(wordJson).get("result").get(
                                        "word").asText();
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("Error processing JSON", e);
                            }
                        })
                        .flatMap(wordRandom -> webClient.get()
                                                        .uri(uriBuilder -> uriBuilder.path(String.format(
                                                                "%s/%s",
                                                                vocabularyProperties.getDictionaryPath(),
                                                                wordRandom
                                                        )).build())
                                                        .retrieve()
                                                        .bodyToMono(String.class))
                        .map(html -> {
                            VocabularyWord vocabularyWord = vocabularyParser.parse(html);

                            return WordMapper.INSTANCE.vocabularyToDictionary(vocabularyWord);
                        });

    }
}
