/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sri.validaciones;
/**
 *
 * @author jack
 */
public class CedulaRuc {

    public static String comprobacion(String cadena){
        String mensaje = "";

        while(cadena.length() <= 13){
            cadena = cadena + " ";
        }

        boolean e1 = !(Integer.parseInt(cadena.substring(0, 2)) >= 1 && Integer.
                parseInt(cadena.substring(0, 2)) <= 24);
        boolean e2 = (!(Integer.parseInt(cadena.substring(2, 3)) >= 0 && Integer.
                parseInt(cadena.substring(2, 3)) <= 6) && (Integer.parseInt(
                cadena.substring(2, 3)) != 9));
        boolean e3 = (!cadena.substring(10, 13).equals("001") && !(cadena.
                substring(10, 13).trim().isEmpty()));
        int n1=0, n2=0, n3=0, n4=0, n5=0, n6=0, n7=0, n8=0, n9=0, n10=0,
                resultado1=0, resultado2=0, resultado3=0;

        if (e1 || e2 || e3){
            if (e1){
                mensaje = "Error en los dos primeros digitos... Debe ser un valor"
                        + " entre '01' y '22'.";
            }else{
                if(e2){
                    mensaje = "Error en los dos primeros digitos... Debe ser un "
                            + "valor entre '0' y '6' para PN; '6' para \nSociedades"
                            + " Públicas; o '9' para Sociedades Privadas..";
                }else{
                    if(e3){
                        mensaje = "Debe terminar en '0001' para Sociedades "
                                + "Públicas o en '001' para Sociedades Privadas.";
                    }
                }
            }
        }else{
            try {
                n1 = Integer.parseInt(cadena.substring(0, 1));
                n2 = Integer.parseInt(cadena.substring(1, 2));
                n3 = Integer.parseInt(cadena.substring(2, 3));
                n4 = Integer.parseInt(cadena.substring(3, 4));
                n5 = Integer.parseInt(cadena.substring(4, 5));
                n6 = Integer.parseInt(cadena.substring(5, 6));
                n7 = Integer.parseInt(cadena.substring(6, 7));
                n8 = Integer.parseInt(cadena.substring(7, 8));
                n9 = Integer.parseInt(cadena.substring(8, 9));
                n10 = Integer.parseInt(cadena.substring(9, 10));

                ///si no ocurre error sigue el algoritmo

                //Para la Cédula o Ruc de Personas Naturales
                resultado1 = operacionCedula(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10)%10;
                if (resultado1 != 0)
                    mensaje = "Cedula incorrecta";

                //Para Ruc Sociedades Privadas
                resultado2 = ((n1*4) + (n2*3) + (n3*2) + (n4*7) + (n5*6) + (n6*5) +
                        (n7*4) + (n8*3) + (n9*2) + n10) % 11;

                //Para Ruc Sociedades Públicas
                resultado3 = ((n1*3) + (n2*2) + (n3*7) + (n4*6) + (n5*5) + (n6*4) +
                        (n7*3) + (n8*2) + n9) % 11;


                if (((resultado1 == 0 && (Integer.parseInt(cadena.substring(2, 3)) >= 0
                        && Integer.parseInt(cadena.substring(2, 3)) <= 6) && (cadena.
                        substring(10, 13).equals("001") || cadena.substring(10, 13).trim().
                        isEmpty())) || (resultado2 == 0 && n3 == 9 && cadena.substring(
                        10, 13).equals("001"))) || (resultado3 == 0 && n3 == 6 && cadena.
                        substring(9, 13).equals("0001"))){
                    mensaje = "T";
                }else{
                    mensaje = "F" + mensaje;
                }

            } catch (NumberFormatException numberFormatException) {
                mensaje = "FNo ha ingresado los caracteres suficientes en la identificación para proceder";
            }
        }
        return mensaje;
    }

    public static int operacionCedula(int n1, int n2, int n3, int n4, int n5,
            int n6, int n7, int n8, int n9, int n10){
        int n1Aux, n3Aux, n5Aux, n7Aux, n9Aux, resultado;

        if (n1 > 4){
            n1Aux = n1 * 2 - 9;
        }else{
            n1Aux = n1 * 2;
        }

        if (n3 > 4){
            n3Aux = n3 * 2 - 9;
        }else{
            n3Aux = n3 * 2;
        }

        if (n5 > 4){
            n5Aux = n5 * 2 - 9;
        }else{
            n5Aux = n5 * 2;
        }

        if (n7 > 4){
            n7Aux = n7 * 2 - 9;
        }else{
            n7Aux = n7 * 2;
        }

        if (n9 > 4){
            n9Aux = n9 * 2 - 9;
        }else{
            n9Aux = n9 * 2;
        }
        resultado = n1Aux + n2 + n3Aux + n4 + n5Aux + n6 + n7Aux + n8 + n9Aux + n10;
        return resultado;
    }

}
