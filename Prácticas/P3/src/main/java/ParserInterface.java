package main.java;

public interface ParserInterface {

    /**
     * Método que nos permite solicitar un siguiente token al 
     * analizador léxico siempre que la entrada empate con
     * el símbolo terminal esperado. 
     * @param claseLexica el símbolo terminal esperado
     */
    public void eat(int claseLexica);

    /**
     * Método que nos permite imprimir un error sintáctico
     * @param msg la descripción del error
     */
    public void error(String msg);

    /**
     * Método que comienza el proceso de análisis sintáctico, 
     * llama por primera vez al método del símbolo inicial
     */
    public void parse();

    /**
     * Método del símbolo incial, una vez que termina sin reportar error,
     * se concluye que la cadena pertenece al lenguaje.
     */
    public void S();
}