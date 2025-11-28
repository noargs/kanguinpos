package com.swingpos.utils;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;

public class PrintFormat {

    private PrinterJob printerJob;

    public PrintFormat(PrinterJob printerJob)
    {
        this.printerJob = printerJob;
    }

    public PageFormat getPageFormat()
    {

        PageFormat pf = printerJob.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = 8.0;
        double headerHeight = 1.0;
        double footerHeight = 1.0;

        // printer only understand point per inch (ppi), default is 72ppi
        double width = cmToPPI(8);
        double height = cmToPPI(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - cmToPPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);

        return pf;
    }

    private static double cmToPPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    private static double toPPI(double inch) {
        return inch * 72d;
    }
}
