package Componente;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.event.*;

public class PasswordPanel extends JPanel {
    private JPasswordField pwdCelda;
    private JLabel visibilidadLabel, generarLabel, seguridadLabel;
    private boolean pwdVisible;
    private ImageIcon mostrar, ocultar, generar;
    private int longitud;
    
    // Banderas para controlar los tipos de caracteres
    private boolean incluirMayusculas;
    private boolean incluirMinusculas;
    private boolean incluirNumeros;
    private boolean incluirEspeciales;

    public PasswordPanel() {
        // Configuración del panel
        setLayout(new FlowLayout());

        // Cargar las imágenes
        mostrar = new ImageIcon(getClass().getResource("/resources/Mostrar.png")); // Ruta de la imagen "mostrar"
        ocultar = new ImageIcon(getClass().getResource("/resources/Ocultar.png")); // Ruta de la imagen "ocultar"
        generar = new ImageIcon(getClass().getResource("/resources/Recargar.png")); // Ruta de la imagen "generar"

        // Crear el campo de contraseña
        pwdCelda = new JPasswordField(15);
        pwdCelda.setEchoChar('*'); // Oculta el texto con asteriscos
        pwdCelda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePasswordSecurity(String.valueOf(pwdCelda.getPassword()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePasswordSecurity(String.valueOf(pwdCelda.getPassword()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePasswordSecurity(String.valueOf(pwdCelda.getPassword()));
            }
        });
        
        add(pwdCelda);

        // Crear la etiqueta para alternar visibilidad con el ícono de mostrar
        visibilidadLabel = new JLabel(mostrar);
        visibilidadLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        visibilidadLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility();
            }
        });
        add(visibilidadLabel);

        // Crear la etiqueta para generar contraseña con el ícono de generar
        generarLabel = new JLabel(generar);
        generarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generatePassword(longitud);
            }
        });
        add(generarLabel);
        
        // Crear la etiqueta para mostrar el nivel de seguridad de la contraseña
        seguridadLabel = new JLabel("Seguridad: ");
        seguridadLabel.setForeground(Color.RED); // Color inicial para una contraseña baja
        add(seguridadLabel);

        // Estado inicial de visibilidad y longitud
        pwdVisible = false;
        longitud = 8; // Longitud predeterminada de la contraseña

        // Estado inicial de los tipos de caracteres
        incluirMayusculas = true;
        incluirMinusculas = true;
        incluirNumeros = true;
        incluirEspeciales = true;
    }

    // Método para establecer la longitud de la contraseña
    public void setLongitud(int l) {
        this.longitud = l;
    }

    // Métodos para configurar los tipos de caracteres
    public void setIncluirMayusculas(boolean incluir) {
        this.incluirMayusculas = incluir;
    }

    public void setIncluirMinusculas(boolean incluir) {
        this.incluirMinusculas = incluir;
    }

    public void setIncluirNumeros(boolean incluir) {
        this.incluirNumeros = incluir;
    }

    public void setIncluirEspeciales(boolean incluir) {
        this.incluirEspeciales = incluir;
    }

    // Método para alternar la visibilidad de la contraseña
    private void togglePasswordVisibility() {
        if (pwdVisible) {
            pwdCelda.setEchoChar('*');
            visibilidadLabel.setIcon(mostrar); // Cambia a ícono de "mostrar"
        } else {
            pwdCelda.setEchoChar((char) 0); // Muestra el texto
            visibilidadLabel.setIcon(ocultar); // Cambia a ícono de "ocultar"
        }
        pwdVisible = !pwdVisible;
    }

    // Método para generar una contraseña aleatoria
    private void generatePassword(int longitud) {
        String generatedPassword = generateRandomPassword(longitud);
        pwdCelda.setText(generatedPassword); // Actualizar la seguridad de la contraseña
        updatePasswordSecurity(generatedPassword); // Actualizar seguridad después de generar la contraseña
    }

    // Generador de contraseña aleatoria
    private String generateRandomPassword(int length) {
        StringBuilder characters = new StringBuilder();

        // Construir el conjunto de caracteres en base a las opciones configuradas
        if (incluirMayusculas) characters.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (incluirMinusculas) characters.append("abcdefghijklmnopqrstuvwxyz");
        if (incluirNumeros)    characters.append("0123456789");
        if (incluirEspeciales) characters.append("!@#$%^&*()");

        // Verificar que al menos un tipo de carácter esté seleccionado
        if (characters.length() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione al menos un tipo de carácter para generar la contraseña.");
            return "";
        }

        // Generar la contraseña aleatoria
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
    
    // Método para evaluar y actualizar la seguridad de la contraseña
    // Método para actualizar la seguridad de la contraseña
    private void updatePasswordSecurity(String password) {
        int securityLevel = calculatePasswordSecurity(password);

        // Cambiar el texto y el color según el nivel de seguridad
        if (securityLevel < 3) {
            seguridadLabel.setText("Seguridad: Baja");
            seguridadLabel.setForeground(Color.RED);
        } else if (securityLevel < 4) {
            seguridadLabel.setText("Seguridad: Media");
            seguridadLabel.setForeground(Color.YELLOW);
        } else {
            seguridadLabel.setText("Seguridad: Fuerte");
            seguridadLabel.setForeground(Color.GREEN);
        }
    }

    // Método para calcular el nivel de seguridad de la contraseña
    private int calculatePasswordSecurity(String password) {
        int level = 0;

        if (password.length() >= 8) level++; // Incrementa por longitud adecuada
        if (password.matches(".*[A-Z].*")) level++; // Contiene mayúsculas
        if (password.matches(".*[a-z].*")) level++; // Contiene minúsculas
        if (password.matches(".*\\d.*")) level++; // Contiene números
        if (password.matches(".*[[!@#$%^&*()]].*")) level++; // Contiene caracteres especiales

        return level;
    }

    public static void main(String[] args) {
        // Configurar el JFrame
        JFrame frame = new JFrame("Password Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Crear e integrar el PasswordPanel
        PasswordPanel passwordPanel = new PasswordPanel();
        frame.add(passwordPanel, BorderLayout.CENTER);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
