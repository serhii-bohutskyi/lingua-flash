package com.bohutskyi.fx.linguaflash.dictionary.vocabulary;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dictionary.vocabulary")
public class VocabularyProperties {

    private String baseUrl;
    private String randomPath;
    private String dictionaryPath;

}
