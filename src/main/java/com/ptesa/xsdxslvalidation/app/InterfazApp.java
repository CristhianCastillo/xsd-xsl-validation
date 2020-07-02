package com.ptesa.xsdxslvalidation.app;

import javax.swing.*;
import java.awt.*;

public class InterfazApp extends JFrame {
    private static final long serialVersionUID = 1L;

    private PanelFiles pnlFiles;

    private PanelOut pnlOut;

    public InterfazApp() {
        setTitle("Validaciones FE - 3");
        setLayout(new BorderLayout());
        this.pnlOut = new PanelOut();
        this.pnlFiles = new PanelFiles(this.pnlOut);
        add(this.pnlFiles, "North");
        add(this.pnlOut, "Center");
        setSize(new Dimension(600, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo((Component) null);
        setDefaultCloseOperation(3);
        setResizable(true);
    }
}
