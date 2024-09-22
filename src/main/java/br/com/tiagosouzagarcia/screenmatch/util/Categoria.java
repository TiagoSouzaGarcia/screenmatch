package br.com.tiagosouzagarcia.screenmatch.util;

public enum Categoria {
    ACAO("Action"),
    AVENTURA("Adventure"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    TERROR("Terror"),
    SUSPENSE("Suspense"),
    CURTA("Short"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    ANIMACAO("Animation"),
    OUTRA("Other");


    private final String categoriaOmdb;

    Categoria(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    /**
     * Retorna a categoria correspondente à string fornecida.
     *
     * @param text A string que representa a categoria.
     * @return A categoria correspondente.
     * @throws IllegalArgumentException Se nenhuma categoria for encontrada para a string fornecida.
     */
    public static Categoria fromString(String text) {
        try {
            for (Categoria categoria : Categoria.values()) {
                if (categoria.categoriaOmdb.equalsIgnoreCase( text)) {
                    return categoria;
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Nenhuma categoria encontrada para a string fornecida: " + text);
            throw new InvalidCategoryException("Categoria inválida: " + text);
        }
        return Categoria.OUTRA;
    }
}

class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String message) {
        super(message);
    }
}
