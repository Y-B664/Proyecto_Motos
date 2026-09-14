package co.edu.unilibre.gui;

import javax.swing.*;

public class VentanaPrincipal extends JFrame{
    private JPasswordField passwordField1;
    private JTree tree1;
    private JPanel panelPrincipal;


    public VentanaPrincipal(){
        iniciarComponentes();
    }
    private void iniciarComponentes(){
        setContentPane(panelPrincipal);
        setSize(500,500);
        setLocationRelativeTo(null);

    }
    public static void main(String args[]){
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
