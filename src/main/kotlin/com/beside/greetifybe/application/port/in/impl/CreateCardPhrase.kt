package com.beside.greetifybe.application.port.`in`.impl

import com.beside.greetifybe.application.port.`in`.CreateCardPhraseUseCase
import org.springframework.stereotype.Service

@Service
class CreateCardPhrase : CreateCardPhraseUseCase {

    override fun invoke(command: CreateCardPhraseUseCase.Command): CreateCardPhraseUseCase.Result {
        TODO("Not yet implemented")
    }

}
