/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sri.validaciones;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
/**
 *
 * @author jack
 */
public class FiltrarArchivosOds extends FileFilter{
    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Extensiones.getExtension(f);
        if (extension != null) {
            if (extension.equals(Extensiones.ods)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Archivos de OpenOffice Hojas de Cálculo (*.ods)";
    }

    @Override
    public String toString() {
        return "."+Extensiones.ods;
    }
}
