package br.com.tiagosouzagarcia.screenmatch.util;

public enum Categoria {
    ACAO("Action", "Ação"),
    AVENTURA("Adventure", "Aventura"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comédia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    TERROR("Terror", "Terror"),
    SUSPENSE("Suspense", "Suspense"),
    CURTA("Short", "Curta"),
    HORROR("Horror", "Horror"),
    THRILLER("Thriller", "Thriller"),
    ANIMACAO("Animation", "Animação"),
    OUTRA("Other", "Outro");


    private String categoriaOmdb;

    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
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

    /**
     * Retorna a categoria correspondente à string fornecida em português.
     *
     * @param text A string que representa a categoria.
     * @return A categoria correspondente.
     * @throws IllegalArgumentException Se nenhuma categoria for encontrada para a string fornecida.
     */
    public static Categoria fromPortugues(String text) {
        try {
            for (Categoria categoria : Categoria.values()) {
                if (categoria.categoriaPortugues.equalsIgnoreCase( text)) {
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
