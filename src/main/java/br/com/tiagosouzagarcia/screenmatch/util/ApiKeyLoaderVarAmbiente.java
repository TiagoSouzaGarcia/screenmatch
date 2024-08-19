package br.com.tiagosouzagarcia.screenmatch.util;

public class ApiKeyLoaderVarAmbiente {

    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public static String getApiKey() {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("Chave da API da OpenAI não configurada. Configure a variável de ambiente OPENAI_API_KEY.");
        }
        return API_KEY;
    }
}
