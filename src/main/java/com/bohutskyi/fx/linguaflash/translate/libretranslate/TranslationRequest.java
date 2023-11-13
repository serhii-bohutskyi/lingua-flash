package com.bohutskyi.fx.linguaflash.translate.libretranslate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TranslationRequest {

    private String q; // Query or the text to be translated
    private String source; // Source language
    private String target; // Target language
    private String format; // Format of the text
}