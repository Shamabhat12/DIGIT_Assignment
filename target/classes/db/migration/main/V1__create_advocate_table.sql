CREATE TABLE eg_advocate (
    id                      VARCHAR(64) NOT NULL,
    tenantid                VARCHAR(64) NOT NULL,
    applicationnumber       VARCHAR(64),
    barregistrationnumber   VARCHAR(64),
    advocatetype            VARCHAR(64),
    organisationid          VARCHAR(64),
    individualid            VARCHAR(64),
    isactive                BOOLEAN DEFAULT TRUE,
    status                  VARCHAR(64),
    additionaldetails       JSONB,
    createdby               VARCHAR(64),
    lastmodifiedby          VARCHAR(64),
    createdtime             BIGINT,
    lastmodifiedtime        BIGINT,
    CONSTRAINT pk_eg_advocate PRIMARY KEY (id)
);

-- Table to store advocate documents
CREATE TABLE eg_advocate_document (
    id                  VARCHAR(64) NOT NULL,
    advocateid          VARCHAR(64) NOT NULL,
    documenttype        VARCHAR(64),
    filestore           VARCHAR(64),
    documentuid         VARCHAR(64),
    isactive            BOOLEAN DEFAULT TRUE,
    createdby           VARCHAR(64),
    lastmodifiedby      VARCHAR(64),
    createdtime         BIGINT,
    lastmodifiedtime    BIGINT,
    CONSTRAINT pk_eg_advocate_document PRIMARY KEY (id),
    CONSTRAINT fk_eg_advocate_document FOREIGN KEY (advocateid) 
        REFERENCES eg_advocate(id)
);