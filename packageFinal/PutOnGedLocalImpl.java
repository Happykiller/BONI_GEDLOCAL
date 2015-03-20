/**
 * 
 */
package com.gedloca.put;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.document.Document;
import org.bonitasoft.engine.bpm.document.DocumentNotFoundException;
import org.bonitasoft.engine.connector.ConnectorException;

import com.bonitasoft.connectorGEDLocal.LibConnectorGEDLocal;

/**
 *The connector execution will follow the steps
 * 1 - setInputParameters() --> the connector receives input parameters values
 * 2 - validateInputParameters() --> the connector can validate input parameters values
 * 3 - connect() --> the connector can establish a connection to a remote server (if necessary)
 * 4 - executeBusinessLogic() --> execute the connector
 * 5 - getOutputParameters() --> output are retrieved from connector
 * 6 - disconnect() --> the connector can close connection to remote server (if any)
 */
public class PutOnGedLocalImpl extends AbstractPutOnGedLocalImpl {

	@Override
	protected void executeBusinessLogic() throws ConnectorException{
		//Get access to the connector input parameters
		//getId();
		//getPath();
		//getFile();
	
		//var
		Boolean trace = true;
		
		//Init logger
		Logger logger = Logger.getLogger("org.bonitasoft");
		logger.info(this.getClass().getName() + " - execute method executeBusinessLogic.");
		
		Boolean boolReturn = false;
		
		//Init processAPI
		ProcessAPI myProcessAPI = apiAccessor.getProcessAPI();
		
		if(trace){
			logger.info("getId : " + getId().toString());
			logger.info("getPath : " + getPath());
			logger.info("getFileName : " + getFile().getName());
		}
		
		Document myDocument = getFile();
		
		byte[] docContent;
		try {
			docContent = myProcessAPI.getDocumentContent(myDocument.getContentStorageId());
	    	InputStream is = new ByteArrayInputStream(docContent);
			boolReturn = LibConnectorGEDLocal.upFile(getId(), getPath(), getFile().getName(), is);
		} catch (DocumentNotFoundException e) {
			logger.severe(this.getClass().getName() + " - Error DocumentNotFoundException : " + e.getMessage());
		}
	
		setRetour(boolReturn);
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
