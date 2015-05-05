package com.bonitasoft.connectorGEDLocal;

import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.io.*;

/**
 * Created by Fabrice.R on 19/01/2015.
 */
public class LibConnectorGEDLocal {

    private static final Logger uilLogger = Logger.getLogger("com.bonitasoft.groovy");

    public static void trace(String message){
        try {
            uilLogger.info(message);
            System.out.println(message);
        }catch (Exception e) {
            uilLogger.severe("trace - Error : " + e.getMessage());
        }
    }

    public static boolean putFile(Long id, String path, String fileName, InputStream file){
        Boolean retour = false;
        try {
            File fileOutPut = null;
            if(id != null) {
                String strId = id.toString();
                fileOutPut = new File(path + strId + "_" + fileName);
            }else{
                fileOutPut = new File(path + fileName);
            }
            OutputStream output = new FileOutputStream(fileOutPut);
            try {
                IOUtils.copy(file, output);
            } catch (IOException e) {
                trace("putFile - IOException : " + e.getMessage());
            } finally {
                file.close();
                output.close();
            }
            retour = true;
            return retour;
        }catch (Exception e) {
            trace("putFile - Exception : " + e.getMessage());
            return false;
        }
    }

    public static RetourGet getFile(Long id, String path, String fileName){
        RetourGet myRetourGet = new RetourGet();
        try {
            try {
                String curName = null;
                File dir = new File(path);
                if((fileName != null) && (!fileName.equals("")) && (id == null)){
                    curName = fileName;
                    myRetourGet.nameFile = fileName;
                }else {
                    String strId = id.toString();
                    for (File curFile : dir.listFiles()) {
                        if (curFile.getName().startsWith(strId)) {
                            curName = curFile.getName();
                            myRetourGet.nameFile = curName.replace(strId + "_", "");
                            break;
                        }
                    }
                }

                myRetourGet.theStream = new FileInputStream(path + curName);

                Tika tika = new Tika();
                File file = new File(path + curName);
                String mimeType = tika.detect(file);
                myRetourGet.mineType = mimeType;

            } catch (IOException e) {
                trace("getFile - IOException : " + e.getMessage());
            }
            myRetourGet.statut = true;
            return myRetourGet;
        }catch (Exception e) {
            trace("getFile - Exception : " + e.getMessage());
            return null;
        }
    }
}