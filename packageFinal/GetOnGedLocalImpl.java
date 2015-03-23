/**
 *
 */
package com.gedloca.get;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.bonitasoft.engine.connector.ConnectorException;
import org.bonitasoft.engine.bpm.document.DocumentValue;

import com.bonitasoft.connectorGEDLocal.LibConnectorGEDLocal;
import com.bonitasoft.connectorGEDLocal.RetourGet;

/**
 *The connector execution will follow the steps
 * 1 - setInputParameters() --> the connector receives input parameters values
 * 2 - validateInputParameters() --> the connector can validate input parameters values
 * 3 - connect() --> the connector can establish a connection to a remote server (if necessary)
 * 4 - executeBusinessLogic() --> execute the connector
 * 5 - getOutputParameters() --> output are retrieved from connector
 * 6 - disconnect() --> the connector can close connection to remote server (if any)
 */
public class GetOnGedLocalImpl extends AbstractGetOnGedLocalImpl {

    @Override
    protected void executeBusinessLogic() throws ConnectorException{
        //Get access to the connector input parameters
        //getId();

        //var
        Boolean trace = true;
        String headerLog = "[Log : "+this.getClass().getName()+"]";

        //Init logger
        Logger logger = Logger.getLogger("com.bonitasoft.groovy");
        logger.info(headerLog + "Execute method executeBusinessLogic.");

        if(trace){
            logger.info(headerLog + "getId : " + getId());
            logger.info(headerLog + "getPath : " + getPath());
            logger.info(headerLog + "getFileName : " + getFileName());
        }

        RetourGet myReturn = LibConnectorGEDLocal.getFile(getId(), getPath(), getFileName());

        byte[] bytesFileTest = null;
        try {
            bytesFileTest = IOUtils.toByteArray(myReturn.getTheStream());
        } catch (IOException e) {
            logger.info(headerLog + "Error with bytesFileTest : "+e.getMessage());
            e.printStackTrace();
        }

        String mimeType = myReturn.getMineType();

        String fileName = myReturn.getNameFile();

        if(getId() != null){
            fileName=fileName.replace(getId().toString() + "_", "");
        }

        DocumentValue myDocumentValue = new DocumentValue(bytesFileTest, mimeType, fileName);

        logger.info(headerLog + "Finish with outputFile : "+myDocumentValue.toString());
        setOutputFile(myDocumentValue);
    }

    @Override
    public void connect() throws ConnectorException{
        //[Optional] Open a connection to remote server

    }

    @Override
    public void disconnect() throws ConnectorException{
        //[Optional] Close connection to remote server

    }

}