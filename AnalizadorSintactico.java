import java.util.List;

public class AnalizadorSintactico {
    private List<ComponenteLexico> tokens;
    private int posicion = 0;

    public AnalizadorSintactico(List<ComponenteLexico> tokens) {
        this.tokens = tokens;
    }

    public void analizar() throws Exception {
        while (!esFin()) {
            sentencia();
        }
    }

    private void sentencia() throws Exception {
        if (comprobar(ComponenteLexico.Tipo.IDENTIFICADOR)) {
            asignacion();
        } else if (comprobar(ComponenteLexico.Tipo.PALABRA_RESERVADA)) {
            if (actual().getValor().equals("if")) {
                estructuraControl();
            } else {
                avanzar(); // Ignorar otras palabras reservadas por simplicidad
            }
        } else if (!esFin()) {
            throw new Exception("Error Sintáctico: No se reconoce el inicio de la instrucción: " + actual().getValor());
        }
    }

    private void asignacion() throws Exception {
        avanzar(); // Consume ID
        emparejar(ComponenteLexico.Tipo.OPERADOR, "=");
        if (!comprobar(ComponenteLexico.Tipo.NUMERO) && !comprobar(ComponenteLexico.Tipo.IDENTIFICADOR)) {
            throw new Exception("Error Sintáctico: Se esperaba un valor o variable después del '='");
        }
        avanzar(); // Consume valor
        emparejar(ComponenteLexico.Tipo.SIMBOLO, ";");
    }

    private void estructuraControl() throws Exception {
        avanzar(); // Consume 'if'
        emparejar(ComponenteLexico.Tipo.SIMBOLO, "(");
        // Validación simple de condición
        avanzar(); avanzar(); avanzar(); 
        emparejar(ComponenteLexico.Tipo.SIMBOLO, ")");
    }

    private void emparejar(ComponenteLexico.Tipo tipo, String valorEsperado) throws Exception {
        if (actual().getTipo() == tipo && (valorEsperado == null || actual().getValor().equals(valorEsperado))) {
            avanzar();
        } else {
            throw new Exception("Error Sintáctico: Se esperaba '" + valorEsperado + "' pero se encontró '" + actual().getValor() + "'");
        }
    }

    private ComponenteLexico actual() { return tokens.get(posicion); }
    private void avanzar() { if (!esFin()) posicion++; }
    private boolean comprobar(ComponenteLexico.Tipo tipo) { return actual().getTipo() == tipo; }
    private boolean esFin() { return actual().getTipo() == ComponenteLexico.Tipo.FIN_FICHERO; }
}