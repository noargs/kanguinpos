package com.swingpos.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Clock extends JLabel implements ActionListener {
	  String type;
	  SimpleDateFormat sdf;
	 
	  public Clock(String type) {
	    this.type = type;
	    setForeground(Color.BLACK);
	 
	    switch (type) {
	      case "date" : sdf = new SimpleDateFormat("dd MMMM yyyy");
	                    setFont(new Font("Monospaced", Font.PLAIN, 12));
	                    //setHorizontalAlignment(SwingConstants.LEFT);
	                    break;
	      case "time" : sdf = new SimpleDateFormat("hh:mm:ss a");
	                    setFont(new Font("Monospaced", Font.PLAIN, 12));
	                    //setHorizontalAlignment(SwingConstants.RIGHT);
	                    break;
	      case "day"  : sdf = new SimpleDateFormat("EEEE  ");
	                    setFont(new Font("Monospaced", Font.PLAIN, 12));
	                    //setHorizontalAlignment(SwingConstants.CENTER);
	                    break;
	      default     : sdf = new SimpleDateFormat();
	                    break;
	    }
	 
	    Timer t = new Timer(1000, this);
	    t.start();
	  }

	public void actionPerformed(ActionEvent e) {
		Date d = new Date();
	    setText(sdf.format(d));
	}

}
