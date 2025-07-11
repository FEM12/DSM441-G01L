fun main() {

    var statusProgram: Boolean = true;

    do {

        println("\n****************************************");
        println("***** COMPROBADOR DE NÚMEROS PARES *****");
        println("****************************************\n");

        print("? Digita el número a comprobar: ");
        var num: String = readln();

        if(!num.isEmpty()) {

            if(num.toIntOrNull() != null) {
                findOut(num.toInt());
            }
            else { println("✗ ${error}Error:${reset} Solo se aceptan números") }

        }
        else { println("✗ ${error}Error:${reset} Campo vacío") }

        var statusMessage: Boolean = true;

       do {

           print("\n→ ${message}¿Desea volver a comprobar? (s/n):${reset} ");
           var status: String = readln().lowercase();

           if(!status.isEmpty() && (status.contentEquals("s") || status.contentEquals("n"))) {

               if(status.contentEquals("n")){

                   statusMessage = false;
                   statusProgram = false;

               };
               else { statusMessage = false; }

           }
           else { println("\n⚠ ${warning} Seleccione una opción válida${reset}") }

       }
       while(statusMessage);

    }
    while(statusProgram);

}

fun findOut(num: Int) {

    var residue: Int = num % 2;

    if(residue == 0) { println("\n→ El número es par"); }
    else { println("\n→ El número es impar"); }

}