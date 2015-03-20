package com.bonitasoft.connectorGEDLocal;

import java.io.InputStream;

/**
 * Created by Fabrice on 09/02/2015.
 */
public class RetourGet {
    public Boolean statut = false;
    public String nameFile;
    public String mineType;
    public InputStream theStream;

    public String getMineType() {
        return mineType;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public InputStream getTheStream() {
        return theStream;
    }

    public void setTheStream(InputStream theStream) {
        this.theStream = theStream;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }
}
