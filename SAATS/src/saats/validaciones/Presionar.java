/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sri.validaciones;

import java.awt.event.KeyEvent;

/**
 *
 * @author misael
 */
public class Presionar {

final private static char comillaSimple=39;//apostrofe
    final private static char minus = 45;//-
    final private static char underscore = 95;//_
    final private static char arroba = 64;//@
    final private static char slash = 47;// /
    final private static char asterisco = 42;// *
    /**
     * @autor Misayo
     * Metodo que permite presionar solo numeros
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarNumeros(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if((key < '0' || key > '9')
                &&(key != KeyEvent.VK_DELETE)
                &&(key != KeyEvent.VK_BACK_SPACE)
                &&(key != KeyEvent.VK_ENTER))
            evt.consume();
    }


    public static void presionarNumerosPunto(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if((key < '0' || key > '9')
                &&(key != KeyEvent.VK_DELETE)
                &&(key != KeyEvent.VK_BACK_SPACE)
                &&(key != KeyEvent.VK_ENTER)
                &&(key != KeyEvent.VK_PERIOD))
            evt.consume();
    }
    /**
     * @autor Misayo
     * Metodo que permite presionar y convertir letras en mayusculas y la tecla espacio
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarLetrasMayusculasEspacio(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if((key >= 'a' && key <= 'z') || (key == 'ñ') ||
                (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú'))
            evt.setKeyChar((char)(((int)evt.getKeyChar()) - 32));
        else
            if((key != KeyEvent.VK_SPACE) &&
                    (key > '0' || key < '9') &&
                    (key < 'A' || key > 'Z') &&
                    (key != 'Ñ') && (key != 'Á') &&
                    (key != 'É') && (key != 'Í') &&
                    (key != 'Ó') && (key != 'Ú'))
                evt.consume();
    }
    /**
     * @autor Jack Krauser
     * Metodo que permite presionar y convertir letras en mayusculas y la tecla espacio sin permitir la letra Ñ
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarLetrasMayusculasEspacioSinEnie(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if((key >= 'a' && key <= 'z') || //(key != 'ñ') ||
                (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú'))
            evt.setKeyChar((char)(((int)evt.getKeyChar()) - 32));
        else
            if((key != KeyEvent.VK_SPACE) &&
                    (key > '0' || key < '9') &&
                    (key < 'A' || key > 'Z') &&
                    (key != 'Ñ') && (key != 'Á') &&
                    (key != 'É') && (key != 'Í') &&
                    (key != 'Ó') && (key != 'Ú'))
                evt.consume();
    }
    /**
     * @autor Jack Krauser
     * Metodo que permite presionar y convertir letras en mayusculas y numeros;
     * no se permiten presionar la tecla espacio y apostrofe
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarAlfanumericoMayusculas(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if(key >= 'a' && key <= 'z' || (key == 'ñ') ||
                (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú'))
            evt.setKeyChar((char)(((int)evt.getKeyChar()) - 32));
        else
            if((key < '0' || key > '9') &&
                    (key != underscore && key != minus && key != arroba && key != slash && key != asterisco) &&
                    (key < 'A' || key > 'Z') &&
                    (key != 'Ñ') && (key != 'Á') &&
                    (key != 'É') && (key != 'Í') &&
                    (key != 'Ó') && (key != 'Ú'))
                evt.consume();

    }
    /**
     * @autor Jack Krauser
     * Metodo que permite presionar y convertir letras en mayusculas y numeros;
     * no se permiten presionar la tecla apostrofe
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarAlfanumericoEspacioMayusculas(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if(key >= 'a' && key <= 'z' || (key == 'ñ') ||
                (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú'))
            evt.setKeyChar((char)(((int)evt.getKeyChar()) - 32));
        else
            if((key < '0' || key > '9') &&
                    (key != underscore && key != minus 
                    && key != arroba && !(key == KeyEvent.VK_SPACE) && !(key == '%') && key != KeyEvent.VK_PERIOD && key != KeyEvent.VK_COMMA && key != slash && key != asterisco) &&
                    (key < 'A' || key > 'Z') &&
                    (key != 'Ñ') && (key != 'Á') &&
                    (key != 'É') && (key != 'Í') &&
                    (key != 'Ó') && (key != 'Ú'))
                evt.consume();

    }
    /**
     * @autor Misayo
     * Metodo que permite presionar y convertir letras en mayusculas;
     * no se permite presionar la tecla apostrofe
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarCaracteresEspacioMayusculas(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if(key >= 'a' && key <= 'z' ||
                (key == 'á' || key == 'é' || key == 'í' || key == 'ó' || key == 'ú'))
            evt.setKeyChar((char)(((int)evt.getKeyChar()) - 32));
        else
            if((key == comillaSimple) &&
                    (key < 'A' || key > 'Z') &&
                    (key != 'Ñ') && (key != 'Á') &&
                    (key != 'É') && (key != 'Í') &&
                    (key != 'Ó') && (key != 'Ú'))
                evt.consume();
    }
    /**
     * @autor Misayo
     * Metodo que permite presionar cualquier tecla ,
     * ademas presionar numeros; no se permiten presionar el apostrofe
     * @param java.awt.event.KeyEvent evt evento de la tecla presionada
     */
    public static void presionarCaracteresComilla(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if(key == comillaSimple)//'
            evt.consume();
    }

    public static void presionarCaracteresMinusculaEspacioArroba(java.awt.event.KeyEvent evt) {
        char key = evt.getKeyChar();
        if((key == comillaSimple) && (key != arroba) || (key == KeyEvent.VK_SPACE))//'
            evt.consume();
    }
}