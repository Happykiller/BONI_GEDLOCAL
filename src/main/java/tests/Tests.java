package tests;

import com.bonitasoft.connectorGEDLocal.LibConnectorGEDLocal;
import com.bonitasoft.connectorGEDLocal.RetourGet;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by Fabrice.R on 19/01/2015.
 */
public class Tests {
    public static void main(String [ ] args){
        LibConnectorGEDLocal.trace("===== Mes tests ======");
        testPutFile();
        testGetFile();
        testPutFileWithoutId();
        testGetFileWithoutId();
    }

    public static void testPutFile(){
        Boolean boolReturn = false;
        try {
            Long id = 1L;
            String path = "C:/DATAS/ged/";
            String fileName = "mypdf1.pdf";

            InputStream file = new FileInputStream("C:/DATAS/mypdf1.pdf");

            boolReturn = LibConnectorGEDLocal.putFile(id, path, fileName, file);

            if(boolReturn){
                file = new FileInputStream("C:/DATAS/mypdf1.pdf");
                byte[] bytesFile = IOUtils.toByteArray(file);

                InputStream fileAttendu = new FileInputStream(path+"1_"+fileName);

                byte[] bytesFileAttendu = IOUtils.toByteArray(fileAttendu);
                if(bytesFile.length == bytesFileAttendu.length){
                    boolReturn = true;
                }else{
                    boolReturn = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            boolReturn = false;
        } catch (IOException e) {
            e.printStackTrace();
            boolReturn = false;
        }

        LibConnectorGEDLocal.trace("Test testPutFile : " + boolReturn);
    }

    public static void testPutFileWithoutId(){
        Boolean boolReturn = false;
        try {
            String path = "C:/DATAS/ged/";
            String fileName = "mypdf1.pdf";

            InputStream file = new FileInputStream("C:/DATAS/mypdf1.pdf");

            boolReturn = LibConnectorGEDLocal.putFile(null, path, fileName, file);

            if(boolReturn){
                file = new FileInputStream("C:/DATAS/mypdf1.pdf");
                byte[] bytesFile = IOUtils.toByteArray(file);

                InputStream fileAttendu = new FileInputStream(path+fileName);

                byte[] bytesFileAttendu = IOUtils.toByteArray(fileAttendu);
                if(bytesFile.length == bytesFileAttendu.length){
                    boolReturn = true;
                }else{
                    boolReturn = false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            boolReturn = false;
        } catch (IOException e) {
            e.printStackTrace();
            boolReturn = false;
        }

        LibConnectorGEDLocal.trace("Test testPutFileWithoutId : " + boolReturn);
    }

    public static void testGetFile(){
        Boolean boolReturn = false;
        try {
            InputStream fileAttendu = new FileInputStream("C:/DATAS/ged/1_mypdf1.pdf");
            byte[] bytesFileAttendu = IOUtils.toByteArray(fileAttendu);

            Long id = 1L;
            String path = "C:/DATAS/ged/";

            RetourGet myReturn = LibConnectorGEDLocal.getFile(id, path, null);

            File file = new File("C:/DATAS/ged/test.pdf");
            OutputStream output = new FileOutputStream(file);
            IOUtils.copy(myReturn.getTheStream(), output);

            InputStream fileTest = new FileInputStream("C:/DATAS/ged/test.pdf");
            byte[] bytesFileTest = IOUtils.toByteArray(fileTest);

            if(bytesFileTest.length == bytesFileAttendu.length){
                boolReturn = true;
            }else{
                boolReturn = false;
            }

            file.delete();

            myReturn.getTheStream().close();
            fileTest.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            boolReturn = false;
        } catch (IOException e) {
            e.printStackTrace();
            boolReturn = false;
        }

        LibConnectorGEDLocal.trace("Test testGetFile : " + boolReturn);
    }

    public static void testGetFileWithoutId(){
        Boolean boolReturn = false;
        try {
            InputStream fileAttendu = new FileInputStream("C:/DATAS/ged/mypdf1.pdf");
            byte[] bytesFileAttendu = IOUtils.toByteArray(fileAttendu);

            String path = "C:/DATAS/ged/";

            RetourGet myReturn = LibConnectorGEDLocal.getFile(null, path, "mypdf1.pdf");

            File file = new File("C:/DATAS/ged/test.pdf");
            OutputStream output = new FileOutputStream(file);
            IOUtils.copy(myReturn.getTheStream(), output);

            InputStream fileTest = new FileInputStream("C:/DATAS/ged/test.pdf");
            byte[] bytesFileTest = IOUtils.toByteArray(fileTest);

            if(bytesFileTest.length == bytesFileAttendu.length){
                boolReturn = true;
            }else{
                boolReturn = false;
            }

            file.delete();

            myReturn.getTheStream().close();
            fileTest.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            boolReturn = false;
        } catch (IOException e) {
            e.printStackTrace();
            boolReturn = false;
        }

        LibConnectorGEDLocal.trace("Test testGetFileWithoutId : " + boolReturn);
    }
}