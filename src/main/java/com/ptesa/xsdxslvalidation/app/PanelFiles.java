package com.ptesa.xsdxslvalidation.app;

import com.ptesa.xsdxslvalidation.controller.ControllerApp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFiles extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private static final String SELECT_XML_FILE = "Select xml file";

    private static final String SELECT_XSD_FILE = "Select xsd file";

    private static final String SELECT_XSLT_FILE = "Select xslt file";

    private static final String VALIDATE_XSD = "Validate xml file";

    private static final String VALIDATE_XSLT = "Convert xml file";

    private JLabel lblXMLFile;

    private JLabel lblXSDFile;

    private JLabel lblXSLTFile;

    private JLabel lblKeyEncryption;

    private JLabel lblValueCertificate;

    private JTextField txtXMLFile;

    private JTextField txtXSDFile;

    private JTextField txtXSLTFile;

    private JTextField txtKeyEncryption;

    private JTextField txtValueCertificate;

    private JButton btnXMLFile;

    private JButton btnXSDFile;

    private JButton btnXSLTFile;

    private JButton btnValidateXML;

    private JButton btnConvertXML;

    private JButton btnEncrypt;

    private JButton btnDecrypt;

    private JFileChooser jFileChooser;

    private PanelOut pnlOut;

    public PanelFiles(PanelOut pnlOut) {
        this.pnlOut = pnlOut;
        setBorder(new TitledBorder("Archivos XSD - XML"));
        setLayout(new BorderLayout());
        JPanel pnlFiles = new JPanel();
        GroupLayout group = new GroupLayout(pnlFiles);
        pnlFiles.setLayout(group);
        this.lblXMLFile = new JLabel("Archivo XML: ");
        this.lblXSDFile = new JLabel("Archivo XSD: ");
        this.lblXSLTFile = new JLabel("Archivo XSLT");

        this.lblKeyEncryption = new JLabel("Llave Encriptación");
        this.lblValueCertificate = new JLabel("Valor certificado");

        this.txtXMLFile = new JTextField();
        this.txtXMLFile.setEditable(false);
        this.txtXSDFile = new JTextField();
        this.txtXSDFile.setEditable(false);
        this.txtXSLTFile = new JTextField();
        this.txtXSLTFile.setEditable(false);

        this.txtKeyEncryption = new JTextField(20);

        this.txtValueCertificate = new JTextField(20);

        this.btnXMLFile = new JButton("Seleccionar XML");
        this.btnXMLFile.setActionCommand("Select xml file");
        this.btnXMLFile.addActionListener(this);
        this.btnXSDFile = new JButton("Seleccionar XSD");
        this.btnXSDFile.setActionCommand("Select xsd file");
        this.btnXSDFile.addActionListener(this);
        this.btnXSLTFile = new JButton("Seleccionar XSLT");
        this.btnXSLTFile.setActionCommand("Select xslt file");
        this.btnXSLTFile.addActionListener(this);
        this.btnValidateXML = new JButton("Validar Estructura XML");
        this.btnValidateXML.setActionCommand("Validate xml file");
        this.btnValidateXML.addActionListener(this);
        this.btnConvertXML = new JButton("Convertir XML -> UBL 2.1");
        this.btnConvertXML.setActionCommand("Convert xml file");
        this.btnConvertXML.addActionListener(this);

        this.btnEncrypt = new JButton("Encriptar");
        this.btnEncrypt.setActionCommand("Encrypt");
        this.btnEncrypt.addActionListener(this);

        this.btnDecrypt = new JButton("Desencriptar");
        this.btnDecrypt.setActionCommand("Decrypt");
        this.btnDecrypt.addActionListener(this);

        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout(2));
        pnlButtons.add(this.btnXMLFile);
        pnlButtons.add(this.btnXSDFile);
        pnlButtons.add(this.btnXSLTFile);

        JPanel pnlExeButtons = new JPanel();
        pnlExeButtons.setBorder(new TitledBorder("Acciones"));
        pnlExeButtons.setLayout(new FlowLayout());
        pnlExeButtons.add(this.btnValidateXML);
        pnlExeButtons.add(this.btnConvertXML);

        JPanel pnlEncryption = new JPanel();
        pnlEncryption.setBorder(new TitledBorder("Certificados"));
        pnlEncryption.setLayout(new FlowLayout());
        pnlEncryption.add(lblKeyEncryption);
        pnlEncryption.add(txtKeyEncryption);
        pnlEncryption.add(lblValueCertificate);
        pnlEncryption.add(txtValueCertificate);
        pnlEncryption.add(btnEncrypt);
        pnlEncryption.add(btnDecrypt);


        group.setAutoCreateContainerGaps(true);
        group.setAutoCreateGaps(true);
        group.setHorizontalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup().addComponent(this.lblXMLFile).addComponent(this.lblXSDFile)
                        .addComponent(this.lblXSLTFile))

                .addGroup(group.createParallelGroup().addComponent(this.txtXMLFile).addComponent(this.txtXSDFile)
                        .addComponent(this.txtXSLTFile).addComponent(pnlButtons, GroupLayout.Alignment.TRAILING)));
        group.setVerticalGroup(group.createSequentialGroup()
                .addGroup(group.createParallelGroup().addComponent(this.lblXMLFile).addComponent(this.txtXMLFile))

                .addGroup(group.createParallelGroup().addComponent(this.lblXSDFile).addComponent(this.txtXSDFile))

                .addGroup(group.createParallelGroup().addComponent(this.lblXSLTFile).addComponent(this.txtXSLTFile))

                .addGroup(group.createParallelGroup().addComponent(pnlButtons)));
        add(pnlFiles, "North");
        add(pnlExeButtons, "Center");
        add(pnlEncryption, "South");
    }

    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        try {
            if (comand.equals("Select xml file")) {
                this.jFileChooser = new JFileChooser("./data");
                this.jFileChooser.setDialogTitle("Buscar archivo XML PTESA");
                this.jFileChooser.setMultiSelectionEnabled(false);
                int resultado = this.jFileChooser.showOpenDialog(this);
                if (resultado == 0) {
                    String rutaArchivo = this.jFileChooser.getSelectedFile().getAbsolutePath();
                    this.txtXMLFile.setText(rutaArchivo);
                }
            } else if (comand.equals("Select xsd file")) {
                this.jFileChooser = new JFileChooser("./data");
                this.jFileChooser.setDialogTitle("Buscar archivo XSD PTESA");
                this.jFileChooser.setMultiSelectionEnabled(false);
                int resultado = this.jFileChooser.showOpenDialog(this);
                if (resultado == 0) {
                    String rutaArchivo = this.jFileChooser.getSelectedFile().getAbsolutePath();
                    this.txtXSDFile.setText(rutaArchivo);
                }
            } else if (comand.equals("Select xslt file")) {
                this.jFileChooser = new JFileChooser("./data");
                this.jFileChooser.setDialogTitle("Buscar archivo XSLT PTESA");
                this.jFileChooser.setMultiSelectionEnabled(false);
                int resultado = this.jFileChooser.showOpenDialog(this);
                if (resultado == 0) {
                    String rutaArchivo = this.jFileChooser.getSelectedFile().getAbsolutePath();
                    this.txtXSLTFile.setText(rutaArchivo);
                }
            } else if (comand.equals("Validate xml file")) {
                if (this.txtXMLFile.getText().equals("") || this.txtXMLFile.getText() == null)
                    throw new Exception("Nombre de archivo XML no valido.");
                if (this.txtXSDFile.getText().equals("") || this.txtXSDFile.getText() == null)
                    throw new Exception("Nombre de archivo XSD no valido.");
                this.pnlOut.addResult(ControllerApp.validateXML(this.txtXMLFile.getText(), this.txtXSDFile.getText()));
            } else if (comand.equals("Convert xml file")) {
                if (this.txtXMLFile.getText().equals("") || this.txtXMLFile.getText() == null)
                    throw new Exception("Nombre de archivo XML no valido.");
                if (this.txtXSLTFile.getText().equals("") || this.txtXSLTFile.getText() == null)
                    throw new Exception("Nombre de archivo XSLT no valido.");
                this.pnlOut.addResult(ControllerApp.convertFile(this.txtXSLTFile.getText(), this.txtXMLFile.getText()));
            } else if (comand.equals("Encrypt")) {
                if (this.txtKeyEncryption.getText().equals("") || this.txtValueCertificate.getText() == null)
                    throw new Exception("Parametros no pueden estar vacio para encriptar valor.");
                this.pnlOut.addResult(ControllerApp.encryptText(this.txtKeyEncryption.getText(), this.txtValueCertificate.getText()));
            } else if (comand.equals("Decrypt")) {
                if (this.txtKeyEncryption.getText().equals("") || this.txtValueCertificate.getText() == null)
                    throw new Exception("Parametros no pueden estar vacio para descencriptar valor.");
                this.pnlOut.addResult(ControllerApp.decryptText(this.txtKeyEncryption.getText(), this.txtValueCertificate.getText()));
            }

        } catch (
                Exception e2) {
            JOptionPane.showMessageDialog(null, e2.getMessage(), "Exportar archivo", 0);
        }
    }
}
