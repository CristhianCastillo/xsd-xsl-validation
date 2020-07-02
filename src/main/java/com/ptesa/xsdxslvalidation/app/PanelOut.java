package com.ptesa.xsdxslvalidation.app;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelOut extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTabbedPane tabs;

    private int resultNumber;

    public PanelOut() {
        setBorder(new TitledBorder("Resultados"));
        this.resultNumber = 0;
        setLayout(new BorderLayout());
        this.tabs = new JTabbedPane(1, 1);
        add(this.tabs, "Center");
    }

    public String addResult(JComponent component) {
        String titleResult = "";
        try {
            if (component != null) {
                this.resultNumber++;
                titleResult = "Resultado " + this.resultNumber;
                this.tabs.addTab(titleResult, null, component, "Resultado " + this.resultNumber);
                int count = this.tabs.getTabCount();
                this.tabs.setSelectedIndex(count - 1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "", 0);
        }
        return titleResult;
    }
}
