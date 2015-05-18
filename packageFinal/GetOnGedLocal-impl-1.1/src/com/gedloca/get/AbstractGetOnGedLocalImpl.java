package com.gedloca.get;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public abstract class AbstractGetOnGedLocalImpl extends AbstractConnector {

	protected final static String ID_INPUT_PARAMETER = "id";
	protected final static String PATH_INPUT_PARAMETER = "path";
	protected final static String FILENAME_INPUT_PARAMETER = "fileName";
	protected final String OUTPUTFILE_OUTPUT_PARAMETER = "outputFile";

	protected final java.lang.Long getId() {
		return (java.lang.Long) getInputParameter(ID_INPUT_PARAMETER);
	}

	protected final java.lang.String getPath() {
		return (java.lang.String) getInputParameter(PATH_INPUT_PARAMETER);
	}

	protected final java.lang.String getFileName() {
		return (java.lang.String) getInputParameter(FILENAME_INPUT_PARAMETER);
	}

	protected final void setOutputFile(
			org.bonitasoft.engine.bpm.document.DocumentValue outputFile) {
		setOutputParameter(OUTPUTFILE_OUTPUT_PARAMETER, outputFile);
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
			getFileName();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("fileName type is invalid");
		}

	}

}
