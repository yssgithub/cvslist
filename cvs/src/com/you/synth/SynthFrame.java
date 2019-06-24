package com.you.synth;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;


/**
 * 
 * @author MAbernethy
 */
public class SynthFrame extends JFrame {

	private javax.swing.JPanel jContentPane = null;

	private javax.swing.JLabel lblName = null;
	private javax.swing.JLabel lblPass = null;
	private javax.swing.JLabel lblSound = null;
	private javax.swing.JButton btnCancel = null;
	private javax.swing.JButton btnOk = null;
	private javax.swing.JTextField txtName = null;
	private javax.swing.JTextField txtPass = null;
	private javax.swing.JCheckBox checkNew = null;
	
	public static void main(String[] args) throws Exception {

		Runnable runner = new Runnable() {
		  public void run() {
		  	try {
				SynthLookAndFeel synth = new SynthLookAndFeel();
				synth.load(SynthFrame.class.getResourceAsStream("demo.xml"), SynthFrame.class);
				UIManager.setLookAndFeel(synth);
		  	} catch (Exception ex) {
		  		ex.printStackTrace();
		  	}
		  }
		};		
		SwingUtilities.invokeAndWait(runner);

		SynthFrame f = new SynthFrame();		
		f.setVisible(true);
	}

	public SynthFrame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(341, 275);
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
	}

	private javax.swing.JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			java.awt.GridBagConstraints consGridBagConstraints2 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints1 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints3 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints5 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints6 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints7 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints4 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints8 = new java.awt.GridBagConstraints();
			consGridBagConstraints2.insets = new java.awt.Insets(12,36,16,6);
			consGridBagConstraints2.ipady = 4;
			consGridBagConstraints2.ipadx = 25;
			consGridBagConstraints2.gridy = 1;
			consGridBagConstraints2.gridx = 0;
			consGridBagConstraints1.insets = new java.awt.Insets(42,46,12,6);
			consGridBagConstraints1.ipady = 3;
			consGridBagConstraints1.ipadx = 10;
			consGridBagConstraints1.gridy = 0;
			consGridBagConstraints1.gridx = 0;
			consGridBagConstraints5.insets = new java.awt.Insets(13,18,35,7);
			consGridBagConstraints5.ipady = -1;
			consGridBagConstraints5.ipadx = 22;
			consGridBagConstraints5.gridy = 3;
			consGridBagConstraints5.gridx = 1;
			consGridBagConstraints6.insets = new java.awt.Insets(38,6,8,29);
			consGridBagConstraints6.ipady = 7;
			consGridBagConstraints6.ipadx = 166;
			consGridBagConstraints6.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints6.weightx = 1.0;
			consGridBagConstraints6.gridwidth = 2;
			consGridBagConstraints6.gridy = 0;
			consGridBagConstraints6.gridx = 1;
			consGridBagConstraints3.insets = new java.awt.Insets(12,39,14,13);
			consGridBagConstraints3.ipady = 5;
			consGridBagConstraints3.ipadx = 14;
			consGridBagConstraints3.gridy = 2;
			consGridBagConstraints3.gridx = 0;
			consGridBagConstraints8.insets = new java.awt.Insets(13,7,13,42);
			consGridBagConstraints8.ipadx = 28;
			consGridBagConstraints8.gridy = 2;
			consGridBagConstraints8.gridx = 1;
			consGridBagConstraints7.insets = new java.awt.Insets(9,6,12,0);
			consGridBagConstraints7.ipady = 7;
			consGridBagConstraints7.ipadx = 88;
			consGridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints7.weightx = 1.0;
			consGridBagConstraints7.gridy = 1;
			consGridBagConstraints7.gridx = 1;
			consGridBagConstraints4.insets = new java.awt.Insets(13,0,35,34);
			consGridBagConstraints4.ipady = -1;
			consGridBagConstraints4.gridy = 3;
			consGridBagConstraints4.gridx = 2;
			jContentPane.setLayout(new java.awt.GridBagLayout());
			jContentPane.add(getLblName(), consGridBagConstraints1);
			jContentPane.add(getLblPass(), consGridBagConstraints2);
			jContentPane.add(getLblSound(), consGridBagConstraints3);
			jContentPane.add(getBtnCancel(), consGridBagConstraints4);
			jContentPane.add(getBtnOk(), consGridBagConstraints5);
			jContentPane.add(getTxtName(), consGridBagConstraints6);
			jContentPane.add(getTxtPass(), consGridBagConstraints7);
			jContentPane.add(getCheckNew(), consGridBagConstraints8);
		}
		return jContentPane;
	}

	private javax.swing.JLabel getLblName() {
		if(lblName == null) {
			lblName = new javax.swing.JLabel();
			lblName.setText("User Name:");
			lblName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return lblName;
	}

	private javax.swing.JLabel getLblPass() {
		if(lblPass == null) {
			lblPass = new javax.swing.JLabel();
			lblPass.setText("Password:");
			lblPass.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return lblPass;
	}

	private javax.swing.JLabel getLblSound() {
		if(lblSound == null) {
			lblSound = new javax.swing.JLabel();
			lblSound.setText("New User?");
			lblSound.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return lblSound;
	}

	private javax.swing.JButton getBtnCancel() {
		if(btnCancel == null) {
			btnCancel = new javax.swing.JButton();
			btnCancel.setText("Cancel");
		}
		return btnCancel;
	}

	private javax.swing.JButton getBtnOk() {
		if(btnOk == null) {
			btnOk = new javax.swing.JButton();
			btnOk.setText("OK");
		}
		return btnOk;
	}

	private javax.swing.JTextField getTxtName() {
		if(txtName == null) {
			txtName = new javax.swing.JTextField();
		}
		return txtName;
	}

	private javax.swing.JTextField getTxtPass() {
		if(txtPass == null) {
			txtPass = new javax.swing.JTextField();
		}
		return txtPass;
	}

	private javax.swing.JCheckBox getCheckNew() {
		if(checkNew == null) {
			checkNew = new javax.swing.JCheckBox();
		}
		return checkNew;
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
