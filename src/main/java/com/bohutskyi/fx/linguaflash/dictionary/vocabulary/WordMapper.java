package com.bohutskyi.fx.linguaflash.dictionary.vocabulary;

import com.bohutskyi.fx.linguaflash.dictionary.DictionaryWord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordMapper {
    WordMapper INSTANCE = Mappers.getMapper(WordMapper.class);

    DictionaryWord vocabularyToDictionary(VocabularyWord car);
}
