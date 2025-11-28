package com.swingpos.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.swingpos.config.AppConfig;
import com.swingpos.utils.RenderFont;
import net.miginfocom.swing.MigLayout;

public class PayoutWindow extends JPanel {

	private JPanel left;
	private JPanel middle;
	private JPanel right;
	
	private JLabel invisible;
	private JButton cash;
	private JButton card;
	private JButton btnCancel;
	
	private PayoutPad payoutPad;
	private PosOrderTable posOrderTable;
	private OrderTableModel orderTableModel;
	
	private RenderFont renderFont;
	private Font comfortaa_bold;
	
	private JLabel total;

	private ViewListener viewListener;
	private PrintListener printListener;

	public PayoutWindow(PayoutPad payoutPad, OrderTableModel orderTableModel)
	{
		this.orderTableModel = orderTableModel;
		this.payoutPad = payoutPad;

		renderFont = new RenderFont();
		
		try {
			comfortaa_bold = renderFont.customFont(AppConfig.getComfortaBoldFont(), Font.TRUETYPE_FONT, 12f);
		}catch(Exception e)
		{
			
		}
		setLayout(new MigLayout("fill, insets 0"));
		
		
		left = new JPanel(new MigLayout("fill, wrap 1"));
		

		
		cash = new JButton("CASH");
		cash.setPreferredSize(new Dimension(100, 80));
		cash.setFont(comfortaa_bold);
		cash.setBackground(new Color(0, 128, 0));
		cash.setForeground(Color.WHITE);
		left.add(cash);

		cash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(printListener != null)
				{
					printListener.print();
				}
			}
		});
		
		card = new JButton("CARD");
		card.setFont(comfortaa_bold);
		card.setPreferredSize(new Dimension(100, 80));
		left.add(card);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(comfortaa_bold);
		btnCancel.setPreferredSize(new Dimension(100, 80));
		left.add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(viewListener != null)
				{
					viewListener.changeView(AppConfig.TICKET_VIEW);
				}
			}
		});


		
		invisible = new JLabel();
		invisible.setPreferredSize(new Dimension(100, 17));
		left.add(invisible);
		
		
		//payoutPad = new PayoutPad();
		middle = new JPanel();
		middle.add(payoutPad);
		
		
		posOrderTable = new PosOrderTable(orderTableModel);
		right = new JPanel(new MigLayout("fillx, insets 2 1 2 6"));
		right.add(posOrderTable);
		
		
		add(left, "spany");
		add(middle, "spany");
		add(right, "align right top");
	}
	
	
	
	
	
	public void setViewListener(ViewListener listener)
	{
		this.viewListener = listener;
	}
	
	public void  setPaymentListener(PrintListener listener)
    {
        this.printListener = listener;
    }
	
	
	
	
	
	
	
	
	

}
