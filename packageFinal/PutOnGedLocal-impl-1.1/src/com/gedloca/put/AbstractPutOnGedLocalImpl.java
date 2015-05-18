package com.gedloca.put;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public abstract class AbstractPutOnGedLocalImpl extends AbstractConnector {

	protected final static String ID_INPUT_PARAMETER = "id";
	protected final static String PATH_INPUT_PARAMETER = "path";
	protected final static String FILE_INPUT_PARAMETER = "file";
	protected final String RETOUR_OUTPUT_PARAMETER = "retour";

	protected final java.lang.Long getId() {
		return (java.lang.Long) getInputParameter(ID_INPUT_PARAMETER);
	}

	protected final java.lang.String getPath() {
		return (java.lang.String) getInputParameter(PATH_INPUT_PARAMETER);
	}

	protected final org.bonitasoft.engine.bpm.document.Document getFile() {
		return (org.bonitasoft.engine.bpm.document.Document) getInputParameter(FILE_INPUT_PARAMETER);
	}

	protected final void setRetour(java.lang.Boolean retour) {
		setOutputParameter(RETOUR_OUTPUT_PARAMETER, retour);
	}

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getId();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("id type is invalid");
		}
		try {
			getPath();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("path type is invalid");
		}
		try {
			getFile();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("file type is invalid");
		}

	}

}
