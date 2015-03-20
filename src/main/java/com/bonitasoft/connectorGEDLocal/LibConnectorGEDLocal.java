package com.bonitasoft.connectorGEDLocal;

import org.apache.commons.io.IOUtils;

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

    public static String sayHelloMessage(){
        String retour = "HelloWorld";
        try {
            uilLogger.fine("sayHelloMessage - msg : HelloWorld.");
            return retour;
        }catch (Exception e) {
            uilLogger.fine("sayHelloMessage - Error : {}"+ e.getMessage());
            return null;
        }
    }

    public static boolean putFile(Long id, String path, String fileName, InputStream file){
        Boolean retour = false;
        try {
            String strId = id.toString();

            File fileOutPut = new File(path+strId+"_"+fileName);
            OutputStream output = new FileOutputStream(fileOutPut);
            try {
                IOUtils.copy(file, output);
            } catch (IOException e) {
                uilLogger.fine("putFile - Error : {}"+ e.getMessage());
            } finally {
                file.close();
                output.close();
            }
            retour = true;
            return retour;
        }catch (Exception e) {
            uilLogger.fine("putFile - Error : {}"+ e.getMessage());
            return false;
        }
    }

    public static RetourGet getFile(Long id, String path){
        RetourGet myRetourGet = new RetourGet();
        try {
            String strId = id.toString();
            try {
                String curName = null;
                File dir = new File(path);
                String fileName = "";
                for (File curFile : dir.listFiles()) {
                    if (curFile.getName().startsWith(strId)) {
                        curName = curFile.getName();
                        myRetourGet.nameFile = curName.replace(strId +"_","");
                        break;
                    }
                }
                myRetourGet.theStream = new FileInputStream(path+curName);
                Path thePath = Paths.get(path + curName);
                myRetourGet.mineType = Files.probeContentType(thePath);
            } catch (IOException e) {
                uilLogger.fine("getFile - Error : {}"+ e.getMessage());
            }
            myRetourGet.statut = true;
            return myRetourGet;
        }catch (Exception e) {
            uilLogger.fine("getFile - Error : {}"+ e.getMessage());
            return null;
        }
    }
}