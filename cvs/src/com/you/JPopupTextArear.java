package com.you;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class JPopupTextArear extends JTextArea implements MouseListener, ActionListener {

    private static final long serialVersionUID = -406608462064697359L;
    private JPopupMenu popupMenu = null;
    private JMenuItem cutMenu = null, copyMenu = null, pasteMenu = null, selectAllMenu = null;

    private JPopupTextArear() {}
    public JPopupTextArear(String message) {

        super(message);
        popupMenu = new JPopupMenu();

        cutMenu = new JMenuItem("Cut");
        copyMenu = new JMenuItem("Copy");
        pasteMenu = new JMenuItem("Paste");
        selectAllMenu = new JMenuItem("Select All");

        cutMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copyMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pasteMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectAllMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        copyMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        pasteMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        selectAllMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

        cutMenu.addActionListener(this);
        copyMenu.addActionListener(this);
        pasteMenu.addActionListener(this);
        selectAllMenu.addActionListener(this);

        popupMenu.add(cutMenu);
        popupMenu.add(copyMenu);
        popupMenu.add(pasteMenu);
        popupMenu.add(new JSeparator());
        popupMenu.add(selectAllMenu);

        this.add(popupMenu);
        this.addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyMenu) {
            this.copy();
        }
        if (e.getSource() == pasteMenu) {
            this.paste();
        }
        if (e.getSource() == cutMenu) {
            this.cut();
        }
        if (e.getSource() == selectAllMenu) {
            this.selectAll();
        }
    }

    public void mousePressed(MouseEvent e) {
        popupMenuTrigger(e);
    }

    public void mouseReleased(MouseEvent e) {

        popupMenuTrigger(e);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private void popupMenuTrigger(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.requestFocusInWindow();
            cutMenu.setEnabled(isAbleToCopyAndCut());
            copyMenu.setEnabled(isAbleToCopyAndCut());
            pasteMenu.setEnabled(isAbleToPaste());
            selectAllMenu.setEnabled(isAbleToSelectAll());

            popupMenu.show(this, e.getX() + 3, e.getY() + 3);
        }
    }

    private boolean isAbleToSelectAll() {
        return !("".equalsIgnoreCase(this.getText()) || (null == this.getText()));
    }

    private boolean isAbleToCopyAndCut() {
        return (this.getSelectionStart() != this.getSelectionEnd());
    }

    private boolean isAbleToPaste() {

        Transferable content = this.getToolkit().getSystemClipboard().getContents(this);
        try {
            return (content.getTransferData(DataFlavor.stringFlavor) instanceof String);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


//    protected void paintComponent(Graphics g) {
//        try {
////            BufferedImage image;
//            Image image;
////            image = ImageIO.read(new File(MyPanel.class.getResource("/com/you/synth/images/logo_asiainfo.png").getFile()));
//            Toolkit tkb = Toolkit.getDefaultToolkit();
//            image = tkb.getImage(JPopupTextArear.class.getResource("/com/you/synth/images/asiainfo.jpg"));
//
//            super.paintComponent(g);
//            g.drawImage(image, 50, 0, null);
//        } catch (Exception ex) {
//            // handle exception...
//        }
//    }
}

