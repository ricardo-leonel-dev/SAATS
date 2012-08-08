/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package saats.validaciones;

import java.io.FileOutputStream;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

/**
 * Para generar una hoja Excel Simple
 *
 * @author jose
 *
 */
public class Excel {
    
    private static HSSFCellStyle styleStringCabecera;
    private static HSSFCellStyle styleString;
    private static HSSFCellStyle styleInteger;
    private static HSSFCellStyle styleDecimal;
    private static HSSFCellStyle styleDecimalSD;
    private static HSSFCellStyle styleDecimalSF;

    /**
     * Para escribir el contenido de una celda.
     *
     * @param row Row.
     * @param i posicion en la fila.
     * @param value texto a escribir.
     * @param style estilo de la celda.
     */
    public static void createCell(HSSFRow row, int i, String value, HSSFCellStyle style) {
        HSSFCell cell = row.createCell(i);
        value = value+" ";
        if ((value.charAt(0) == 'I' ||
                value.charAt(0) == 'D' ||
                value.charAt(0) == 'C' ||
                value.charAt(0) == 'F') 
                && !value.substring(1).equals(" ")){
            if(value.charAt(0) == 'I')
                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
            if(value.charAt(0) == 'D')
                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            if(value.charAt(0) == 'C')
                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
            if(value.charAt(0) == 'F')
                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
            cell.setCellValue(Double.parseDouble(value.substring(1).trim()));
        }else
            cell.setCellValue(value.substring(1).trim());
        if (value.charAt(0) == 'S'){
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));
            cell.setCellValue(new HSSFRichTextString(value.substring(1).trim()));
        }
        cell.setCellStyle(style);
    }
    //-------------
    /**
     * Crea una hoja Excel con el contenido especificado.
     * @param listaCabecera Vector con los datos a escribir en la hoja.
     * @param listaCuerpo Vector con los datos a escribir en la hoja.
     * @param namesheet nombre de la hoja.
     * @param filename path del fichero donde se escribe.
     */
    public static void crearExcel(java.util.List<String> listaCabecera, java.util.List<String> listaCuerpo, String namesheet, String filename)
            throws Exception {
        try {
            //documento xls
            HSSFWorkbook wb = new HSSFWorkbook();
            //pagina xls
            HSSFSheet sheet = wb.createSheet(namesheet);
//            sheet.getColumnStyle(0).setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
            //estilo (formato)
//            HSSFCellStyle style = wb.createCellStyle();
            styleStringCabecera = wb.createCellStyle();     //caracteres Cabecera
            styleString = wb.createCellStyle();     //caracteres
            styleInteger = wb.createCellStyle();    //numeros enteros
            styleDecimal = wb.createCellStyle();    //numeros decimales con formato
            styleDecimalSD = wb.createCellStyle();    //numeros decimales con formato sin decimal
            styleDecimalSF = wb.createCellStyle();  //numeros decimales sin formato
            //fuente
            HSSFFont fontCabecera = wb.createFont();
            HSSFFont font = wb.createFont();
            //fila
            HSSFRow row;
            for (int i = 0; i < listaCabecera.size() + listaCuerpo.size(); i++) {
                String fila = "";
                if (i < listaCabecera.size())
                    fila = (String) listaCabecera.get(i);
                else
                    fila = (String) listaCuerpo.get(i - listaCabecera.size());
                //Delimitador
                StringTokenizer st = new StringTokenizer(fila, "¬");
                //crear fila
                row = sheet.createRow((short) i);

                int j = 0;
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    // para la cabecera, la primera fila, aplicamos un estilo (negrita y color de fondo azul)
                    if (i <= listaCabecera.size()) {
                        styleStringCabecera = wb.createCellStyle();
                        fontCabecera = wb.createFont();
                        fontCabecera.setFontHeightInPoints((short)8);
                        fontCabecera.setFontName("Arial");
                        fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                        if (i == 0){
                            fontCabecera.setFontHeightInPoints((short)12);
                            fontCabecera.setColor(IndexedColors.AUTOMATIC.getIndex());
//                            fontCabecera.setColor(IndexedColors.RED.getIndex());
                        }
                        styleStringCabecera.setFont(fontCabecera);
                        createCell(row, j, token, styleStringCabecera);
                    }
                    if (i == listaCabecera.size()) {
                        styleStringCabecera = wb.createCellStyle();
                        fontCabecera = wb.createFont();
                        fontCabecera.setFontHeightInPoints((short)10);
                        fontCabecera.setFontName("Arial");
                        fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                        fontCabecera.setColor(IndexedColors.AUTOMATIC.getIndex());
                        styleStringCabecera.setFont(fontCabecera);
                        createCell(row, j, token, styleStringCabecera);
                    }
                    if (i > listaCabecera.size()) {
                        font.setFontHeightInPoints((short)8);
                        font.setFontName("Arial");
                        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
                        font.setColor(IndexedColors.AUTOMATIC.getIndex());
                        if (token.charAt(0) == 'S'){
                            styleString.setFont(font);
                            createCell(row, j, token, styleString);
                        }
                        if (token.charAt(0) == 'I'){
                            styleInteger.setFont(font);
                            createCell(row, j, token, styleInteger);
                        }
                        if (token.charAt(0) == 'D'){
                            styleDecimal.setFont(font);
                            createCell(row, j, token, styleDecimal);
                        }
                        if (token.charAt(0) == 'C'){
                            styleDecimalSD.setFont(font);
                            createCell(row, j, token, styleDecimalSD);
                        }
                        if (token.charAt(0) == 'F'){
                            styleDecimalSF.setFont(font);
                            createCell(row, j, token, styleDecimalSF);
                        }
                    }
                    j = j + 1;
                }
            }
            //Asignar automaticamente el tamaño de las celdas en funcion del contenido
            for (int i = 1; i < listaCabecera.size() + listaCuerpo.size(); i++) {
                sheet.autoSizeColumn((short) i);
            }
            // Escribir el fichero.
            FileOutputStream fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ------------------

    /**
     * Para probar el correcto funcionamiento de la clase.
     * @param args argumentos de entrada.
     */
    public static void main(String[] args) {
        try {
            //Datos a escribir
            java.util.List<String> listaCabecera = new java.util.ArrayList();
            listaCabecera.add("STODOCOMPU");
            listaCabecera.add("SPagina prueba");
            java.util.List<String> listaCuerpo = new java.util.ArrayList();
            listaCuerpo.add(
                "SContable"+"¬"+
                "SFecha"+"¬"+
                "SDocumento"+"¬"+
                "SDebe"+"¬"+
                "SHaber"+"¬"+
                "SSaldo"+"¬"+
                "SObservaciones");
            listaCuerpo.add(
                    "S2011-06 | CE | 0000005"+"¬"+
                    "S2011-06-03"+"¬"+
                    "SCCOM 301"+"¬"+
                    "D1116.95"+"¬"+
                    "F1111.11"+"¬"+
                    "D288.4"+"¬"+
                    "SJOSE CALOZUMA ARMIJOS ~ PR REPOSICIONDE CAJA CHICA SEG CD. 6, 5 CCOM 301, 300, 299, 298, 297, 296, 295, 294"+"¬");
//            listaCuerpo.add(
//                    "SSect."+"¬"+
//                    "SPisc."+"¬"+
//                    "SCor."+"¬"+
//                    "SHas."+"¬"+
//                    "SSiembra"+"¬"+
//                    "SDías"+"¬"+
//                    "SDensidad"+"¬"+
//                    "SLab."+"¬"+
//                    "SNauplio"+"¬"+
//                    "SBal.(LB)"+"¬"+
//                    "SBiomasa"+"¬"+
//                    "SRaleo"+"¬"+
//                    "SConv. Al."+"¬"+
//                    "SGramos"+"¬"+
//                    "SIdeal"+"¬"+
//                    "SSobrev."+"¬"+
//                    "SCosto"+"¬"+
//                    "SCosto Hectarea"+"¬"+
//                    "SCosto Diario"+"¬"+
//                    "SDirecto Diario"+"¬"+
//                    "SIndirecto Diario");
//            listaCuerpo.add(
//                    "SIN¬"
//                    + "S112¬"
////                    + "S04¬"
//                    + "I04¬"
//                    + "D1.39¬"
//                    + "S2010-09-03¬"
////                    + "S276¬"
//                    + "I276¬"
//                    + "D129496¬"
//                    + "SAQUATROPICAL¬"
//                    + "SAQUATROPICAL¬"
//                    + "D¬"
//                    + "D4883¬"
//                    + "D¬"
//                    + "D¬"
//                    + "D35.16¬"
//                    + "D39.43¬"
//                    + "S35%¬"
//                    + "D105.9¬"
//                    + "D76.19¬"
//                    + "D0.28¬"
//                    + "D84.17¬"
//                    + "D21.73");
            // Generar el fichero
//            Excel.crearExcel(listaCabecera, listaCuerpo, "Ejemplo", "demo.xls");
//            sri.validaciones.Fichero.abrirFichero("demo.xls");
//            
        //Read an Excel File and Store in a Vector
        Vector dataHolder=leerExcel("/home/misayo/Escritorio/Compartir/VENTAS.xls");
        //Print the data read
        java.util.List linea = convertirCellDataToArrayList(dataHolder);
        printListToConsole(linea);
//        printCellDataToConsole(dataHolder);
//            String ruta = shrimp.validaciones.Fichero.ruta();
//            if(!ruta.isEmpty()){
//                Excel.crearExcel(listaCabecera, listaCuerpo, "Ejemplo", ruta);
//                shrimp.validaciones.Fichero.abrirFichero(ruta);
//            } else
//                javax.swing.JOptionPane.showMessageDialog(null, "No selecciono ruta..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Vector leerExcel(String fileName)
    {
        /** --Define a Vector
            --Holds Vectors Of Cells
         */
        Vector cellVectorHolder = new Vector();
 
        try{
        /** Creating Input Stream**/
        //InputStream myInput= ReadExcelFile.class.getResourceAsStream( fileName );
        FileInputStream myInput = new FileInputStream(fileName);
 
        /** Create a POIFSFileSystem object**/
        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
 
        /** Create a workbook using the File System**/
         HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
 
         /** Get the first sheet from workbook**/
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
 
        /** We now need something to iterate through the cells.**/
          Iterator rowIter = mySheet.rowIterator();
 
          while(rowIter.hasNext()){
              HSSFRow myRow = (HSSFRow) rowIter.next();
              Iterator cellIter = myRow.cellIterator();
              Vector cellStoreVector=new Vector();
              while(cellIter.hasNext()){
                  HSSFCell myCell = (HSSFCell) cellIter.next();
                  cellStoreVector.addElement(myCell);
              }
              cellVectorHolder.addElement(cellStoreVector);
          }
        }catch (Exception e){e.printStackTrace(); }
        return cellVectorHolder;
    }
 
    public static java.util.List convertirCellDataToArrayList(Vector dataHolder) {
        java.util.List lineas = new java.util.ArrayList();
        for (int i=0; i<dataHolder.size(); i++){
            Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
            java.util.List<String> columnas = new java.util.ArrayList();
            for (int j=0; j<cellStoreVector.size(); j++){
                HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
                columnas.add(myCell.toString().isEmpty() ? null : myCell.toString());
            }
            lineas.add(columnas);
        }
        return lineas;
    }
    
    private static void printListToConsole(java.util.List lineas) {
        for (int i=0; i<lineas.size(); i++){
            java.util.List<String> columnas = (java.util.List<String>) lineas.get(i);
            for (int j=0; j<columnas.size(); j++){
                System.out.print(columnas.get(j) +"\t");
            }
            System.out.println();
        }
    }
    
    private static void printCellDataToConsole(Vector dataHolder) {
        for (int i=0; i<dataHolder.size(); i++){
            Vector cellStoreVector=(Vector)dataHolder.elementAt(i);
            for (int j=0; j<cellStoreVector.size(); j++){
                HSSFCell myCell = (HSSFCell)cellStoreVector.elementAt(j);
                String stringCellValue = myCell.toString();
                System.out.print(stringCellValue+"\t");
            }
            System.out.println();
        }
    }
    // ----
}//end of class Excel.java