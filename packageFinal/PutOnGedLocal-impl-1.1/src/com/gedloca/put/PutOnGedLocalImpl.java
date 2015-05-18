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
		String headerLog = "[Log : "+this.getClass().getName()+"]";

		//Init logger
		Logger logger = Logger.getLogger("com.bonitasoft.groovy");
		logger.info(headerLog + "Execute method executeBusinessLogic.");

		Boolean boolReturn = false;

		//Init processAPI
		ProcessAPI myProcessAPI = apiAccessor.getProcessAPI();

		if(trace){
			logger.info(headerLog + "getId : " + getId());
			logger.info(headerLog + "getPath : " + getPath());
			logger.info(headerLog + "getFileName : " + getFile().getContentFileName());
		}

		Document myDocument = getFile();

		byte[] docContent;
		try {
			docContent = myProcessAPI.getDocumentContent(myDocument.getContentStorageId());
			InputStream is = new ByteArrayInputStream(docContent);
			boolReturn = LibConnectorGEDLocal.putFile(getId(), getPath(), getFile().getContentFileName(), is);
		} catch (DocumentNotFoundException e) {
			logger.info(headerLog + "Error DocumentNotFoundException : " + e.getMessage());
		}

		logger.info(headerLog + "Finish with boolReturn : "+boolReturn.toString());
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