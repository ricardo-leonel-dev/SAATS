/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package saats.validaciones;

import java.math.BigInteger;
import java.util.StringTokenizer;
import org.apache.commons.codec.binary.Hex;
//import seguridad.administracion.ejb30.entity.Sistema;


/**
 *
 * @author Renato
 */
public class Conversiones {

    public static String convierteArrayByte_HexadecimalString(byte[] mac){
        String retorno="";
        int cont=0;
        for(int i=0;i<mac.length;i++){
            cont+=1;           
            retorno+=Hex.encodeHexString(new byte[]{mac[i]});
            if(cont<mac.length)
                retorno+="-";
        }
        return retorno;
    }

     public static byte[] convierteHexadecimalString_ArrayByte(String mac) throws Exception{
         StringTokenizer token=new StringTokenizer(mac,"-");
         byte []retorno=new byte[token.countTokens()];
         int i=0;
         while(token.hasMoreTokens()){
             String conv=token.nextToken();             
             retorno[i]=(new BigInteger(conv,16)).byteValue();
             i++;
         }         
         return retorno;
    }
}
