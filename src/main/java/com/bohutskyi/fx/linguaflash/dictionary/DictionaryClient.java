package com.bohutskyi.fx.linguaflash.dictionary;

import reactor.core.publisher.Mono;

public interface DictionaryClient {

    Mono<DictionaryWord> randomWord();
}
