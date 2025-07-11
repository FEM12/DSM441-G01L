class Product(val name: String, val amount: Int, val price: Double)

class Inventory {

    companion object{

        private var listOfProducts: MutableList<Product> = mutableListOf();

        fun addProduct() {

            print("\n? Nombre: ")
            val name = readln();

            print("? Cantidad: ")
            val amount = readln();

            print("? Precio: ")
            val price = readln();

            if(!name.isEmpty() && !amount.isEmpty() && !price.isEmpty()) {

                if(amount.toIntOrNull() != null && price.toIntOrNull() != null) {

                    val product: Product = Product(name, amount.toInt(), price.toDouble());
                    listOfProducts.add(product);

                }
                else { println("✗ ${error}Error:${reset} Solo se aceptan números") }

            }
            else { println("✗ ${error}Error:${reset} Campos vacíos") }

        }

        fun listProducts() {

            if(!listOfProducts.isEmpty()) {
                for(product: Product in listOfProducts) {
                    println("| ${product.name} | ${product.amount} | ${product.price} |")
                }
            }
            else { println("⚠ ${warning}No hay productos registrados${reset}") }

        }

        fun findProduct() {

            print("\n? Nombre: ")
            val name = readln();

            val productsFound = listOfProducts.filter { it.name == name }

            if(!productsFound.isEmpty()) {
                for(productFound in productsFound) {
                    println("| ${productFound.name} | ${productFound.amount} | ${productFound.price} |")
                }
            }
            else { println("⚠ ${warning}No se encontró ningún producto con ese nombre${reset}") }


        }

    }

}

fun main() {

    var statusProgram: Boolean = true;

    do {

        println("\n********************************");
        println("***** GESTIÓN DE PRODUCTOS *****");
        println("********************************\n");

        println("1) Agregar producto");
        println("2) Listar productos");
        println("3) Buscar productos");
        println("4) Salir\n");

        print("→ Selecciona una opción: ");
        val opc: String = readln();

        if(!opc.isEmpty() && opc.toIntOrNull() != null) {
            when(opc) {
                "1" -> Inventory.addProduct();
                "2" -> Inventory.listProducts();
                "3" -> Inventory.findProduct();
                "4" -> statusProgram = false;
                else -> println("Opción no válida")
            }
        }
        else { println("✗ ${error}Error:${reset} Seleccione una opción válida") }


    }
    while(statusProgram);

}