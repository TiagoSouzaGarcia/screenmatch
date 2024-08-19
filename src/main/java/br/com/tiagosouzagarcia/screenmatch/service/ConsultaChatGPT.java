package br.com.tiagosouzagarcia.screenmatch.service;


import br.com.tiagosouzagarcia.screenmatch.util.ApiKeyLoader;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {

    public static String obterTraducao(String texto) {
        var filePath = "F:\\WS\\ws-Alura\\alura-ws\\programacao\\java\\spring\\api_keys\\api_key_openia.txt";
       OpenAiService service = new OpenAiService(ApiKeyLoader.carregarChaveApi(filePath));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}