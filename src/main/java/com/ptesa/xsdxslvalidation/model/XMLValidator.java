package com.ptesa.xsdxslvalidation.model;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class XMLValidator {
    public List<SAXParseException> validate(String xmlFile, String schemaFile) throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = schemaFactory.newSchema(new File(schemaFile));
        Validator validator = schema.newValidator();
        final List<SAXParseException> exceptionsList = new LinkedList<SAXParseException>();
        validator.setErrorHandler(new ErrorHandler() {
            public void warning(SAXParseException exception) throws SAXException {
                exceptionsList.add(exception);
            }

            public void fatalError(SAXParseException exception) throws SAXException {
                exceptionsList.add(exception);
            }

            public void error(SAXParseException exception) throws SAXException {
                exceptionsList.add(exception);
            }
        });
        validator.validate(new StreamSource(new File(xmlFile)));
        return exceptionsList;
    }
}
