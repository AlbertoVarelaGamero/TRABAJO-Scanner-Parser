
public class ComponenteLexico {
    public enum Tipo {
        PALABRA_RESERVADA, IDENTIFICADOR, OPERADOR, NUMERO, SIMBOLO, FIN_FICHERO, ERROR
    }

    private Tipo tipo;
    private String valor;

    public ComponenteLexico(Tipo tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String toString() {
        // Formato de salida: <ETIQUETA, "valor">
        return String.format("<%-18s, \"%s\">", tipo, valor);
    }

    // Getters para el Parser
    public Tipo getTipo() { return tipo; }
    public String getValor() { return valor; }
}