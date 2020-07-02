package com.ptesa.xsdxslvalidation.controller;

import com.ptesa.xsdxslvalidation.model.XMLValidator;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;

public class ControllerApp {
    public static final String QR_CODE = "https://muisca.dian.gov.co/WebFacturaelectronica/paginas/VerificarFacturaElectronicaExterno.faces?TipoDocumento=1NroDocumento=PRUE980078932NITFacturador=890101815NumIdentAdquiriente=900108281Cufe=bf8b43bc09bdf01204dd2f1dcae2b51ea02e849f29257140fc00dc9b65aa953e";

    public static final String CUFE_DEFAULT = "248de1b0d000fe17d56b975118513374258793cbcadb453627a2c7491885c5a226f3ef2e152bb6843934d7fc0f12a01e";

    public static final String ALGORITHM_DEFAULT = "CUFE-SHA384";

    public static final String PREFIJO_FACTURA = "SETP";

    public static final String NUMERO_MATRICULA_MERCANTIL = "10181";

    public static String getQRCode() {
        return "https://muisca.dian.gov.co/WebFacturaelectronica/paginas/VerificarFacturaElectronicaExterno.faces?TipoDocumento=1NroDocumento=PRUE980078932NITFacturador=890101815NumIdentAdquiriente=900108281Cufe=bf8b43bc09bdf01204dd2f1dcae2b51ea02e849f29257140fc00dc9b65aa953e";
    }

    public static String getCufe() {
        return "248de1b0d000fe17d56b975118513374258793cbcadb453627a2c7491885c5a226f3ef2e152bb6843934d7fc0f12a01e";
    }

    public static String getCufeAlgorithm() {
        return "CUFE-SHA384";
    }

    public static String getPrefijoFactura() {
        return "SETP";
    }

    public static String getNumeroMatricula() {
        return "10181";
    }

    public static JComponent convertFile(String urlXSLT, String urlXML) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File stylesheet = new File(urlXSLT);
        File datafile = new File(urlXML);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(datafile);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = tFactory.newTransformer(stylesource);
        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String strResult = writer.toString();
        JTextArea txtBufferOut = new JTextArea(10, 50);
        txtBufferOut.setLineWrap(true);
        txtBufferOut.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtBufferOut);
        scroll.setHorizontalScrollBarPolicy(30);
        scroll.setVerticalScrollBarPolicy(22);
        txtBufferOut.append(strResult + "\n");
        return scroll;
    }

    public static JComponent validateXML(String xmlPTESA, String xmlXSD) throws Exception {
        Locale.setDefault(Locale.US);
        XMLValidator XMLValidator = new XMLValidator();
        List<SAXParseException> exceptionsList = XMLValidator.validate(xmlPTESA, xmlXSD);
        JTextArea txtBufferOut = new JTextArea(10, 50);
        txtBufferOut.setLineWrap(true);
        txtBufferOut.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtBufferOut);
        scroll.setHorizontalScrollBarPolicy(30);
        scroll.setVerticalScrollBarPolicy(22);
        if (exceptionsList.isEmpty()) {
            txtBufferOut.append("Archivo procesado correctamente\n");
        } else {
            for (SAXParseException exception : exceptionsList) {
                txtBufferOut.append(exception.getMessage() + " - " + exception.getLineNumber() + "*" + exception
                        .getColumnNumber() + "\n");
                txtBufferOut.append(exception.getLocalizedMessage() + "\n");
                txtBufferOut.append(exception.getPublicId() + "\n");
                txtBufferOut.append(exception.getSystemId() + "\n");
                txtBufferOut.append(exception.toString() + "\n");
            }
        }
        return scroll;
    }
}
