package co.edu.unilibre.gui;

import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.datos.TipoPago;
import co.edu.unilibre.gestion.GestorParqueadero;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class VentanaPrincipal extends JFrame{
    private JPasswordField passwordField1;
    private JTree tree1;
    private JPanel panelPrincipal;
    private JTabbedPane tabbedHome;
    private JPanel panelHome;
    private JPanel panelForms;
    private JPanel panelIngreso;
    private JPanel panelSalida;
    private JPanel panelTituloIngreso;
    private JPanel panelFormsIngreso;
    private JPanel panelFormsSalida;
    private JPanel panelTituloSalida;
    private JButton registrarIngresoButton;
    private JTextField fieldBuscarPlaca;
    private JTextField fieldBuscarMoto;
    private JLabel etiquetaCedula;
    private JLabel etiquetaPlaca;
    private JLabel etiquetaMarca;
    private JTextField fieldCedula;
    private JTextField fieldPlaca;
    private JTextField fieldMarca;
    private JComboBox boxTipoPago;
    private JTextField fieldTiempoTranscurrido;
    private JTextField fieldTotalAPagar;
    private JPanel panelParqMotos;
    private JButton buscarPlacaButton;
    private JButton buttonConfirmarSalida;
    private JTable tablaRegistros;
    private JButton actualizarRegistrosButton;
    private JLabel labelTotalMotos;
    private JLabel labelIngresos;
    private JLabel labelCupos;
    private JTextField buscarRegistro;
    private JLabel fieldLogo;
    private GestorParqueadero gestorParqueadero = new GestorParqueadero();
    private ArrayList<Moto> motosMostrar;


    public VentanaPrincipal(){
        gestorParqueadero.crearParqueadero();
        iniciarComponentes();



    }
    private void iniciarComponentes(){
        setContentPane(panelPrincipal);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        panelParqMotos.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonConfirmarSalida.setEnabled(false);
        fieldTiempoTranscurrido.setEnabled(false);
        fieldTotalAPagar.setEnabled(false);
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/Logo.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon iconoPequeño = new ImageIcon(imagenEscalada);
        fieldLogo.setIcon(iconoPequeño);
        actualizaBoxTipoPago();
        actualizarTablaRegistros();
        registrarIngresoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String cedula = fieldCedula.getText().trim();
                    String placa = fieldPlaca.getText().trim().toUpperCase();
                    if (!placa.matches("^[A-Z]{3}-\\d{2}[A-Z]$")) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Formato de placa inválido. Debe ser LLL-NNL (Ej: ABC-12D).", "Error de formato", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    String marca = fieldMarca.getText().trim();
                    if(cedula.isBlank() || placa.isBlank() || marca.isBlank()){
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Los cedula, placa y marca no pueden estar vacios.", "Validación nulos", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (!cedula.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "la cedula debe ser un numero", "Validación nulos", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                Moto motoIngresando = gestorParqueadero.crearMoto(cedula,placa,marca);
                    boolean registrada = gestorParqueadero.ocuparEspacio(motoIngresando);

                    if (registrada){
                        RegistroPago registro = gestorParqueadero.generarRegistroPago(motoIngresando);
                        gestorParqueadero.registrarPago(registro);
                        actualizarMotosVista();
                        fieldCedula.setText("");
                        fieldPlaca.setText("");
                        fieldMarca.setText("");
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Moto registrada");

                    }else{
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "No hay cupos o la moto ya esta registrada");
                    }
            }
        });
        buscarPlacaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = fieldBuscarPlaca.getText().trim().toUpperCase(Locale.ROOT);
                System.out.println(placa);

                if(placa.isBlank()) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Campo vacio.", "Validación nulos", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!placa.matches("^[A-Z]{3}-\\d{2}[A-Z]$")) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Formato de placa inválido. Debe ser LLL-NNL (Ej: ABC-12D).", "Error de formato", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(VentanaPrincipal.this, "¿Dar salida?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    Moto moto = gestorParqueadero.encontrarMoto(placa);
                    if (moto == null) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Placa " + placa + " no existe", "Validación placa", JOptionPane.WARNING_MESSAGE);
                        fieldBuscarPlaca.setText("");
                        return;
                    }
                    fieldBuscarPlaca.setEnabled(false);
                    buscarPlacaButton.setEnabled(false);
                    fieldBuscarPlaca.setText(placa);
                    RegistroPago registro = gestorParqueadero.encontrarRegistroMoto(placa);
                    gestorParqueadero.darSalidaMoto(placa);
                    String tiempoTranscurrido = String.valueOf(Duration.between(registro.obtenerFechaHoraIngreso(),registro.obtenerFechaHoraSalida()).toMinutes());
                    fieldTiempoTranscurrido.setText(tiempoTranscurrido + " minutos.");
                    double valorAPagar = gestorParqueadero.calcularValorAPagar(placa);
                    fieldTotalAPagar.setText(String.valueOf(valorAPagar));
                    buttonConfirmarSalida.setEnabled(true);


                } else {
                    System.out.println("Operación cancelada.");
                }


            }
        });
        buttonConfirmarSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceTipoPago = boxTipoPago.getSelectedIndex();
                if (indiceTipoPago == 0) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "Por favor elegir el tipo de pago", "Validación tipo pago", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String placa = fieldBuscarPlaca.getText();
                RegistroPago registro = gestorParqueadero.encontrarRegistroMoto(placa);
                gestorParqueadero.darSalidaMoto(placa);
                gestorParqueadero.generarPago(TipoPago.values()[indiceTipoPago-1],placa, registro.obtenerValorAPagar());
                actualizarMotosVista();
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Pago y salida realizados correctamente", "Sar salida", JOptionPane.WARNING_MESSAGE);
                fieldBuscarPlaca.setText("");
                fieldBuscarPlaca.setEnabled(true);
                fieldTotalAPagar.setText("");
                fieldTiempoTranscurrido.setText("");
                boxTipoPago.setSelectedIndex(0);
                buttonConfirmarSalida.setEnabled(false);
                buscarPlacaButton.setEnabled(true);
            }
        });
        tabbedHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                actualizarTablaRegistros();
                actualizarReporte();

            }
        });
        actualizarRegistrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTablaRegistros();
                actualizarReporte();
            }
        });
        fieldBuscarMoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(fieldBuscarMoto.getText().trim().toUpperCase().isBlank()){
                    actualizarMotosVista();
                }
                Moto moto = gestorParqueadero.encontrarMoto(fieldBuscarMoto.getText().trim().toUpperCase());
                if(moto == null){
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "La moto no existe", "Moto inexistente", JOptionPane.WARNING_MESSAGE);
                    return;
                }else{
                    panelParqMotos.removeAll();
                    RegistroPago registroPago = gestorParqueadero.encontrarRegistroMoto(moto.obtenerPlaca());
                    panelParqMotos.add(crearTarjetaMoto(moto.obtenerPlaca(),registroPago.obtenerFechaHoraIngreso()));
                    panelParqMotos.revalidate();
                    panelParqMotos.repaint();
                }
            }
        });
        buscarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = buscarRegistro.getText().trim().toUpperCase();
                if (placa.isBlank()) {
                    actualizarTablaRegistros();
                }
                RegistroPago rPago = gestorParqueadero.encontrarRegistroMoto(placa);
                if (rPago == null) {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "La moto no existe", "Moto inexistente", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {

                    String[] nombresColumnas = {" ", "Placa", "Marca", "Cédula", "Hora Ingreso", "Hora Salida", "Valor a pagar", "Valor pagado"};
                    DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);
                    tablaRegistros.setModel(modeloTabla);
                    ArrayList<RegistroPago> registrosPagos = gestorParqueadero.obtenerParqueadero().obtenerRegistroPagos();
                    ArrayList<RegistroPago> registroPagosEncontrados = new ArrayList<>();
                    for (RegistroPago registroPago : registrosPagos) {
                        if (registroPago.obtenerMoto().obtenerPlaca().equals(placa)) {
                            registroPagosEncontrados.add(registroPago);
                        }
                    }
                    for (RegistroPago registroPagoEncontrado : registroPagosEncontrados.reversed()) {
                        Moto moto = registroPagoEncontrado.obtenerMoto();
                        String fechaIngreso = registroPagoEncontrado.obtenerFechaHoraIngreso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        String fechaSalida;
                        if (registroPagoEncontrado.obtenerFechaHoraSalida() == null) {
                            fechaSalida = "---";
                        } else {
                            fechaSalida = registroPagoEncontrado.obtenerFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        }
                        String valorAPagar;
                        if (registroPagoEncontrado.obtenerValorAPagar() == 0) {
                            valorAPagar = "0--";
                        } else {
                            valorAPagar = String.valueOf(registroPagoEncontrado.obtenerValorAPagar());
                        }
                        String valorPagado;
                        if (registroPagoEncontrado.obtenerValorPagado() == 0) {
                            valorPagado = "0--";
                        } else {
                            valorPagado = String.valueOf(registroPagoEncontrado.obtenerValorPagado());
                        }
                        Object[] fila = new Object[]{
                                registrosPagos.indexOf(registroPagoEncontrado) + 1,
                                moto.obtenerPlaca(),
                                moto.obtenerMarca(),
                                moto.obtenerCedulaPropietario(),
                                fechaIngreso,
                                fechaSalida,
                                valorAPagar,
                                valorPagado
                        };
                        modeloTabla.addRow(fila);
                    }
                }

            }
        });
    }

    private void actualizarMotosVista() {
        panelParqMotos.removeAll();
        Collection<Moto> motos = gestorParqueadero.obtenerParqueadero().obtenerMotos().values();
        for (Moto moto: motos){
            RegistroPago registroPago = gestorParqueadero.encontrarRegistroMoto(moto.obtenerPlaca());
            panelParqMotos.add(crearTarjetaMoto(moto.obtenerPlaca(),registroPago.obtenerFechaHoraIngreso()));

        }
        labelCupos.setText(String.valueOf(motos.size())+"/"+"23");
        panelParqMotos.revalidate();
        panelParqMotos.repaint();


    }
    private JPanel crearTarjetaMoto(String placa, LocalDateTime fechaIngreso) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBackground(Color.decode("#262659"));
        tarjeta.setBorder(BorderFactory.createLineBorder(Color.decode("#5D5890"), 2));
        tarjeta.setPreferredSize(new Dimension(130, 100));

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/IconMoto.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoPequeño = new ImageIcon(imagenEscalada);

        JLabel labelIcono = new JLabel(iconoPequeño, SwingConstants.CENTER);

        JLabel labelPlaca = new JLabel(placa, SwingConstants.CENTER);
        labelPlaca.setOpaque(true);
        labelPlaca.setBackground(Color.WHITE);
        labelPlaca.setForeground(Color.BLACK);
        labelPlaca.setFont(new Font("JetBrains Mono", Font.BOLD, 14));

        labelPlaca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaHora = fechaIngreso.format(formato);
        JLabel labelFecha = new JLabel(fechaHora, SwingConstants.CENTER);
        labelFecha.setFont(new Font("JetBrains Mono", Font.BOLD, 12)); // Fuente un poco más pequeña
        labelFecha.setForeground(Color.white);
        labelFecha.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));

        tarjeta.add(labelFecha, BorderLayout.NORTH);
        tarjeta.add(labelIcono, BorderLayout.CENTER);
        tarjeta.add(labelPlaca, BorderLayout.SOUTH);

        return tarjeta;
    }

    private void actualizaBoxTipoPago(){
        boxTipoPago.addItem("");
        for (TipoPago tipoPago : TipoPago.values()) {
            boxTipoPago.addItem(tipoPago.name().toLowerCase());
        }
    }
    private void actualizarTablaRegistros(){
        tablaRegistros.removeAll();
        String[] nombresColumnas = {" ","Placa", "Marca", "Cédula", "Hora Ingreso","Hora Salida","Valor a pagar", "Valor pagado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(nombresColumnas, 0);
        tablaRegistros.setModel(modeloTabla);
        ArrayList<RegistroPago> registrosPagos = gestorParqueadero.obtenerParqueadero().obtenerRegistroPagos();
        for(RegistroPago registroPago : registrosPagos.reversed()){
            Moto moto = registroPago.obtenerMoto();
            String fechaIngreso = registroPago.obtenerFechaHoraIngreso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            String fechaSalida;
            if (registroPago.obtenerFechaHoraSalida() == null){
                fechaSalida = "---";
            }else{
                fechaSalida = registroPago.obtenerFechaHoraSalida().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            }
            String valorAPagar;
            if (registroPago.obtenerValorAPagar() == 0){
                valorAPagar = "0--";
            }else{
                valorAPagar = String.valueOf(registroPago.obtenerValorAPagar() );
            }
            String valorPagado;
            if (registroPago.obtenerValorPagado() == 0){
                valorPagado = "0--";
            }else{
                valorPagado = String.valueOf(registroPago.obtenerValorPagado() );
            }
            Object[] fila = new Object[]{
                    registrosPagos.indexOf(registroPago)+1,
                    moto.obtenerPlaca(),
                    moto.obtenerMarca(),
                    moto.obtenerCedulaPropietario(),
                    fechaIngreso,
                    fechaSalida,
                    valorAPagar,
                    valorPagado
            };
            modeloTabla.addRow(fila);
        }
    }

    private void actualizarReporte(){
        double[] reporte = gestorParqueadero.generarReporte();
        labelTotalMotos.setText(String.valueOf((int)reporte[0]));
        labelIngresos.setText(String.valueOf(reporte[1]));
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }

    private void createUIComponents() {

    }
}
