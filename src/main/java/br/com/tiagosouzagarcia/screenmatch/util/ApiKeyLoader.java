package br.com.tiagosouzagarcia.screenmatch.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiKeyLoader {

    public static String carregarChaveApi(String filePath) {
        try {
            String apiKey = new String(Files.readAllBytes(Paths.get(filePath)));
            apiKey = apiKey.trim();

            return apiKey;
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo da chave da API: " + e.getMessage());
        }
        return "";
    }
}
