/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package saats.validaciones;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author misayo
 */
public class Propiedades {

    private static Properties propPrincipal;
    private String ip;
    private String mac;
    private String host;
    private InetAddress direccionLocal;
    //Linux ruta por defecto    "/home/usuario/"
    //windows ruta por defecto  "a lado del jar"
    private static String rutaPropiedades = "./PropiedadesPrincipales.properties";//"prop\\";
//    private static String rutaPropiedades = System.getProperty("user.dir")+"/PropiedadesPrincipales.properties";//"prop\\";

    public Propiedades() throws UnknownHostException, SocketException, Exception{
        estableceInterfaz();
    }

    private void estableceInterfaz() throws UnknownHostException, SocketException, Exception{
        for(Enumeration e = NetworkInterface.getNetworkInterfaces();e.hasMoreElements();){
            NetworkInterface ni = (NetworkInterface)e.nextElement();
            for(Enumeration ee = ni.getInetAddresses(); ee.hasMoreElements();) {
                InetAddress ipLocal = (InetAddress)ee.nextElement();
                direccionLocal = ipLocal;
                if(!ni.isLoopback()){//lo
                    if (ni.isUp()){//si esta activa
                        if (direccionLocal.getHostAddress().trim().length() < 17){//mac en base decimal
//                            host = direccionLocal.getHostName();
                            ip = direccionLocal.getHostAddress();
                            mac = Conversiones.convierteArrayByte_HexadecimalString(ni.getHardwareAddress());
                        }
                    }
                }
            }
        }
    }

    public String getIp() throws Exception{
        return ip;
    }

    public String getHost() throws Exception{
        return host;
    }

    public String getMac() throws Exception{
        return mac;
    }

    public static Properties readPropiedades() throws java.io.IOException, Exception{
        if(propPrincipal==null)
        {
            propPrincipal=new java.util.Properties();
            java.io.InputStream is = null;

            is=new java.io.FileInputStream(rutaPropiedades);
            propPrincipal.load(is);
        }
        return propPrincipal;
    }

    public Properties writePropiedades() throws java.io.IOException, Exception{
        if(propPrincipal==null)
        {
            propPrincipal.setProperty("usuario.ip", getIp());
            escribePropiedades();

            propPrincipal.setProperty("usuario.host", getHost());
            escribePropiedades();

            propPrincipal.setProperty("usuario.mac", getMac());
            escribePropiedades();
        }
        return propPrincipal;
    }

    public void escribePropiedades() throws Exception {
        java.io.OutputStream os=new java.io.FileOutputStream(rutaPropiedades);
        propPrincipal.store(os, "");
    }

//z    public static void main(String arg[]){
//        try {
//            Propiedades propiedades1 = new Propiedades();
//            System.out.println(propiedades1.getIp());
//            System.out.println(propiedades1.getHost());
//            System.out.println(propiedades1.getMac());
//            propiedades1.readPropiedades();
//            propiedades1.writePropiedades();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
}