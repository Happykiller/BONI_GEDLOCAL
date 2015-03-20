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
        System.out.println("===== Mes tests ======");
        testPutFile();
        testGetFile();
    }

    public static void testSayHelloMessage(){
        String strRetour = "";
        String attendu = "HelloWorld";
        strRetour = LibConnectorGEDLocal.sayHelloMessage();
        System.out.println("Test sayHelloMessage : " + strRetour.equals(attendu));
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

        System.out.println("Test testPutFile : " + boolReturn);
    }

    public static void testGetFile(){
        Boolean boolReturn = false;
        try {
            InputStream fileAttendu = new FileInputStream("C:/DATAS/ged/1_mypdf1.pdf");
            byte[] bytesFileAttendu = IOUtils.toByteArray(fileAttendu);

            Long id = 1L;
            String path = "C:/DATAS/ged/";

            RetourGet myReturn = LibConnectorGEDLocal.getFile(id, path);
            System.out.println("=>getMineType : " + myReturn.getMineType());
            System.out.println("=>getNameFile : " + myReturn.getNameFile());

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

        System.out.println("Test testGetFile : " + boolReturn);
    }
}