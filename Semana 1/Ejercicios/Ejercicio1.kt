import kotlin.system.exitProcess

fun main() {

    var statusProgram: Boolean = true;

    do {

        println("\n**********************************************");
        println("***** CALCULADORA DE OPERACIONES BÁSICAS *****");
        println("**********************************************\n");

        println("1) Suma");
        println("2) Resta");
        println("3) Multiplicación");
        println("4) División");
        println("5) Salir\n")

        print("? Selecciona una operación matemática: ");
        val opc: String = readln();

        if(opc.contentEquals("5")) { exitProcess(0) }

        print("? Digite el primer número: ");
        val num1: String = readln();

        print("? Digite el segundo número: ");
        val num2: String = readln();

        if(!opc.isEmpty() && !num1.isEmpty() && !num2.isEmpty()) {

            if(num1.toIntOrNull() != null && num2.toIntOrNull() != null) {
                calculate(num1.toInt(), num2.toInt(), opc)
            }
            else { println("\n✗ ${error}Error:${reset} Solo se aceptan números") }

        }
        else { println("\n✗ ${error}Error:${reset} Campos vacíos") }

    }
    while(statusProgram);

}

fun calculate(num1: Int, num2: Int, opc: String) {

    var result = when(opc){
        "1" -> num1 + num2
        "2" -> num1 - num2
        "3" -> num1 * num2
        "4" -> {
            if(num1 != 0 && num2 != 0) { num1 / num2 }
            else { "⚠ ${warning}No es posible dividir entre 0${reset}" }
        }
        else -> "✗ ${error}Error:${reset} Operación no soportada"
    };

    println("\n→ $result");

}