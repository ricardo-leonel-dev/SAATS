/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sri.validaciones;

/**
 *
 * @author misayo
 */
public class PresentarInformacion {

    /**
     * @autor misayo
     * @param java.util.List <String> lista
     * @throws Exception Cualquier error inesperado
     */
    //lista.get(0) = mensaje
    //lista.get(1) = cabecera
    //lista.get(1+n) = cuerpo
    public static void presentarInformacion(java.util.List <String> lista, javax.swing.JFrame jFrame){
        if(lista.size()<1)
            javax.swing.JOptionPane.showInternalMessageDialog(jFrame, lista.get(0).substring(1));
        else{
            javax.swing.JPanel jPanel = new javax.swing.JPanel();
            //añadir un Panel superior para el borderLayout
            javax.swing.JPanel jPanelTitulo = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
            javax.swing.JPanel jPanelContenido = new javax.swing.JPanel();
            //expandir el contenedor
            jPanelContenido.setLayout(new javax.swing.BoxLayout(jPanelContenido, javax.swing.BoxLayout.Y_AXIS));
            //crea un borderLayout
            jPanel.setLayout(new java.awt.BorderLayout());
            javax.swing.JLabel jLabel = new javax.swing.JLabel();
            javax.swing.DefaultListModel defaultListModel = new javax.swing.DefaultListModel();
            for(String elemento : lista)
                defaultListModel.addElement(elemento);
            javax.swing.JList jLista = new javax.swing.JList();
            jLista.setModel(defaultListModel);
            jLabel.setText(lista.get(0).substring(1));
            jPanelTitulo.add(jLabel);
            jPanelContenido.add(new javax.swing.JScrollPane(jLista), java.awt.BorderLayout.CENTER);
            //añade los paneles al borderLayout
            jPanel.add(jPanelTitulo, java.awt.BorderLayout.NORTH);
            jPanel.add(jPanelContenido, java.awt.BorderLayout.CENTER);
            javax.swing.JOptionPane.showInternalMessageDialog(jFrame, jPanel);
        }
    }
}
