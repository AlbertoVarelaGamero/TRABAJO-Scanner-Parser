import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    public static List<ComponenteLexico> analizar(String entrada) {
        List<ComponenteLexico> tokens = new ArrayList<>();
        
        // Expresión regular para: Palabras reservadas, Identificadores, Números, Operadores y Símbolos
        String regex = "(?<RESERVADA>\\b(if|else|while|int|float|return|print)\\b)|" +
                       "(?<ID>[a-zA-Z_][a-zA-Z0-9_]*)|" +
                       "(?<NUM>\\d+(\\.\\d+)?)|" +
                       "(?<OP>[+\\-*/=<>!]+)|" +
                       "(?<SIMB>[(){};,])";

        Pattern patron = Pattern.compile(regex);
        Matcher matcher = patron.matcher(entrada);

        while (matcher.find()) {
            if (matcher.group("RESERVADA") != null) 
                tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.PALABRA_RESERVADA, matcher.group("RESERVADA")));
            else if (matcher.group("ID") != null) 
                tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.IDENTIFICADOR, matcher.group("ID")));
            else if (matcher.group("NUM") != null) 
                tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.NUMERO, matcher.group("NUM")));
            else if (matcher.group("OP") != null) 
                tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.OPERADOR, matcher.group("OP")));
            else if (matcher.group("SIMB") != null) 
                tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.SIMBOLO, matcher.group("SIMB")));
        }
        
        tokens.add(new ComponenteLexico(ComponenteLexico.Tipo.FIN_FICHERO, "EOF"));
        return tokens;
    }
}