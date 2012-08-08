/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sri.validaciones;
import java.io.File;
import javax.swing.filechooser.*;

/**
 *
 * @author jack
 */
public class FiltrarArchivosXls extends FileFilter {
    public boolean accept(File f) {
        String extension = "";
        if (f.isDirectory()) {
            return true;
        }

        extension = Extensiones.getExtension(f);
        if (extension != null) {
            if (extension.equals(Extensiones.xls)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Archivos XLS (*.xls)";
    }

    @Override
    public String toString() {
        return "."+Extensiones.xls;
    }
}
