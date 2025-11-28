package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.utils.IconFactory;
import com.swingpos.utils.RenderFont;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PayoutPad extends JPanel implements ActionListener {

	private JLabel lblDueAmount;
	private JLabel lblPaidAmount;
	private JTextField txtDue;
	private JTextField txtPaid;
	
	private JButton btnCurrency1;
	private JButton btnCurrency2;
	private JButton btnCurrency5;
	private JButton btnCurrency10;
	private JButton btnCurrency20;
	private JButton btnCurrency50;
	private JButton btnClearAll;
	private JButton btn0;
	private JButton btn00;
	private JButton btnDot;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	
	private JPanel row1;
	private JPanel row2;
	private JPanel row3;
	private JPanel row4;
	private JPanel row5;
	private JPanel row6;
	private JPanel row7;

	private RenderFont fontRendering;
	private Font comfortaa_bold;

	private static final String[] currency = new String[]{"0.00", "1.00", "2.00", "5.00", "10.00", "20.00", "50.00"};

	GridBagConstraints gc = new GridBagConstraints();
	GridBagLayout layout = new GridBagLayout();
	
	public PayoutPad()
	{
        initFont();

		initLayout();

		btnCurrency1.addActionListener(this);
		btnCurrency2.addActionListener(this);
		btnCurrency5.addActionListener(this);
		btnCurrency10.addActionListener(this);
		btnCurrency20.addActionListener(this);
		btnCurrency50.addActionListener(this);

		btnClearAll.addActionListener(this);
		btn0.addActionListener(this);
		btn00.addActionListener(this);
		btnDot.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn5.addActionListener(this);
		btn6.addActionListener(this);
		btn7.addActionListener(this);
		btn8.addActionListener(this);
		btn9.addActionListener(this);

		
	}

	private void initFont()
	{
		fontRendering = new RenderFont();

		try {
			comfortaa_bold = fontRendering.customFont(AppConfig.getComfortaBoldFont(), Font.TRUETYPE_FONT, 35f);
		}catch(Exception e)
		{
		}
	}

	public JTextField getTxtDue()
	{
		return txtDue;
	}

	public JTextField getTxtPaid()
	{
		return txtPaid;
	}

	public void resetTxtPaid()
	{
		setTxtPaid(currency[0]);
	}

	public void setTxtPaid(String amount)
	{
		txtPaid.setText(amount);
	}

	public void appendPaidAmount(String paid)
	{
		List<String> list = Arrays.asList(currency);

		if(list.contains(txtPaid.getText()))
		{
			setTxtPaid(paid);
		}
		else
		{
			txtPaid.setText(txtPaid.getText() + paid);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if(clicked == btnClearAll)
		{
			resetTxtPaid();
		}
		else if(clicked == btnCurrency1)
		{
			setTxtPaid(currency[1]);
		}
		else if(clicked == btnCurrency2)
		{
			setTxtPaid(currency[2]);
		}
		else if(clicked == btnCurrency5)
		{
			setTxtPaid(currency[3]);
		}
		else if(clicked == btnCurrency10)
		{
			setTxtPaid(currency[4]);
		}
		else if(clicked == btnCurrency20)
		{
			setTxtPaid(currency[5]);
		}
		else if(clicked == btnCurrency50)
		{
			setTxtPaid(currency[6]);
		}
		else if(clicked == btn0)
		{
			appendPaidAmount("0");
		}
		else if(clicked == btn00)
		{
			appendPaidAmount("00");
		}
		else if(clicked == btnDot)
		{
			appendPaidAmount(".");
		}
		else if(clicked == btn1)
		{
			appendPaidAmount("1");
		}
		else if(clicked == btn2)
		{
			appendPaidAmount("2");
		}
		else if(clicked == btn3)
		{
			appendPaidAmount("3");
		}
		else if(clicked == btn4)
		{
			appendPaidAmount("4");
		}
		else if(clicked == btn5)
		{
			appendPaidAmount("5");
		}
		else if(clicked == btn6)
		{
			appendPaidAmount("6");
		}
		else if(clicked == btn7)
		{
			appendPaidAmount("7");
		}
		else if(clicked == btn8)
		{
			appendPaidAmount("8");
		}
		else if(clicked == btn9)
		{
			appendPaidAmount("9");
		}

	}

	private void initLayout()
	{
		setLayout(layout);
		row1 = new JPanel(new FlowLayout());

		lblDueAmount = new JLabel();
		lblDueAmount.setText("Due:  " + AppConfig.CURRENCY);
		lblDueAmount.setPreferredSize(new Dimension(208, 40));
		lblDueAmount.setFont(new Font(null, Font.BOLD, 16));
		lblDueAmount.setForeground(Color.GRAY);
		lblDueAmount.setHorizontalAlignment(SwingConstants.RIGHT);


		txtDue = new JTextField();
		txtDue.setText("0.00");
		txtDue.setPreferredSize(new Dimension(120, 40));
		txtDue.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txtDue.setFont(new Font(null, Font.BOLD, 30));
		txtDue.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDue.setEditable(false);


		row1.add(lblDueAmount);
		row1.add(txtDue);


		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 0);
		add(row1, gc);

		row2 = new JPanel(new FlowLayout());
		lblPaidAmount = new JLabel();
		lblPaidAmount.setText("Paid:  " + AppConfig.CURRENCY);
		lblPaidAmount.setPreferredSize(new Dimension(208, 40));
		lblPaidAmount.setFont(new Font(null, Font.BOLD, 16));
		lblPaidAmount.setForeground(Color.GRAY);
		lblPaidAmount.setHorizontalAlignment(SwingConstants.RIGHT);

		txtPaid = new JTextField();
		txtPaid.setText("0.00");
		txtPaid.setPreferredSize(new Dimension(120, 40));
		txtPaid.setFont(new Font(null, Font.BOLD, 25));
		txtPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPaid.setEditable(false);
		txtPaid.setBackground(Color.WHITE);

		row2.add(lblPaidAmount);
		row2.add(txtPaid);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 5, 0);
		add(row2, gc);


		row3 = new JPanel(new FlowLayout());

		btnCurrency1 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR1, new Dimension(80, 80)));
		btnCurrency1.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency1.setPreferredSize(new Dimension(80, 70));

		btn7 = new JButton();
		btn7.setText("7");
		btn7.setFont(comfortaa_bold);
		btn7.setPreferredSize(new Dimension(80, 70));

		btn8 = new JButton();
		btn8.setText("8");
		btn8.setFont(comfortaa_bold);
		btn8.setPreferredSize(new Dimension(80, 70));

		btn9 = new JButton();
		btn9.setText("9");
		btn9.setFont(comfortaa_bold);
		btn9.setPreferredSize(new Dimension(80, 70));

		row3.add(btnCurrency1);
		row3.add(btn7);
		row3.add(btn8);
		row3.add(btn9);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(-5, 0, 0, 0);
		add(row3, gc);

		row4 = new JPanel(new FlowLayout());

		btnCurrency2 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR2, new Dimension(80, 80)));
		btnCurrency2.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency2.setPreferredSize(new Dimension(80, 70));

		btn4 = new JButton();
		btn4.setText("4");
		btn4.setFont(comfortaa_bold);
		btn4.setPreferredSize(new Dimension(80, 70));

		btn5 = new JButton();
		btn5.setText("5");
		btn5.setFont(comfortaa_bold);
		btn5.setPreferredSize(new Dimension(80, 70));

		btn6 = new JButton();
		btn6.setText("6");
		btn6.setFont(comfortaa_bold);
		btn6.setPreferredSize(new Dimension(80, 70));

		row4.add(btnCurrency2);
		row4.add(btn4);
		row4.add(btn5);
		row4.add(btn6);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(-5, 0, 0, 0);
		add(row4, gc);

		row5 = new JPanel(new FlowLayout());

		btnCurrency5 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR5, new Dimension(80, 40)));
		btnCurrency5.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency5.setPreferredSize(new Dimension(80, 70));

		btn1 = new JButton();
		btn1.setText("1");
		btn1.setFont(comfortaa_bold);
		btn1.setPreferredSize(new Dimension(80, 70));

		btn2 = new JButton();
		btn2.setText("2");
		btn2.setFont(comfortaa_bold);
		btn2.setPreferredSize(new Dimension(80, 70));

		btn3 = new JButton();
		btn3.setText("3");
		btn3.setFont(comfortaa_bold);
		btn3.setPreferredSize(new Dimension(80, 70));

		row5.add(btnCurrency5);
		row5.add(btn1);
		row5.add(btn2);
		row5.add(btn3);

		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(-5, 0, 0, 0);
		add(row5, gc);

		row6 = new JPanel(new FlowLayout());

		btnCurrency10 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR10, new Dimension(80, 40)));
		btnCurrency10.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency10.setPreferredSize(new Dimension(80, 70));

		btn0 = new JButton();
		btn0.setText("0");
		btn0.setFont(comfortaa_bold);
		btn0.setPreferredSize(new Dimension(80, 70));

		btn00 = new JButton();
		btn00.setText("00");
		btn00.setFont(comfortaa_bold);
		btn00.setPreferredSize(new Dimension(80, 70));

		btnDot = new JButton();
		btnDot.setText(".");
		btnDot.setFont(comfortaa_bold);
		btnDot.setPreferredSize(new Dimension(80, 70));

		row6.add(btnCurrency10);
		row6.add(btn0);
		row6.add(btn00);
		row6.add(btnDot);

		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(-5, 0, 0, 0);
		add(row6, gc);

		row7 = new JPanel(new FlowLayout());

		btnCurrency20 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR20, new Dimension(80, 40)));
		btnCurrency20.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency20.setPreferredSize(new Dimension(80, 70));
		btnCurrency20.setForeground(Color.BLUE);

		btnCurrency50 = new JButton(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.EUR50, new Dimension(80, 40)));
		btnCurrency50.setFont(new Font(null, Font.BOLD, 26));
		btnCurrency50.setPreferredSize(new Dimension(80, 70));

		btnClearAll = new JButton();
		btnClearAll.setText("CLEAR ALL");
		btnClearAll.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.getIconClear()));
		btnClearAll.setIconTextGap(-3);
		btnClearAll.setMargin(new Insets(0, -2, 0, 2));
		btnClearAll.setPreferredSize(new Dimension(164, 70));


		row7.add(btnCurrency20);
		row7.add(btnCurrency50);
		row7.add(btnClearAll);

		gc.gridx = 0;
		gc.gridy = 6;
		gc.gridwidth = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(-5, 0, 0, 0);
		add(row7, gc);
	}
}
