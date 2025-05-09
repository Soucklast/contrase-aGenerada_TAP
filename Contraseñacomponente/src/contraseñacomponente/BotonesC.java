package contraseñacomponente;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BotonesC extends JPanel {

    private JLabel OCULTAR;
    private JLabel VER;
    private JButton jButton1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPasswordField passwordField;
    private JProgressBar securityBar;

    public BotonesC() {
        initComponents();
        this.OCULTAR.setVisible(false);

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSecurityLevel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSecurityLevel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSecurityLevel();
            }

            private void updateSecurityLevel() {
                String password = new String(passwordField.getPassword());
                int securityLevel = password.isEmpty() ? 0 : Calculabarraseguridad(password);
                securityBar.setValue(securityLevel);
            }
        });
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        passwordField = new JPasswordField();
        VER = new JLabel();
        OCULTAR = new JLabel();
        jButton1 = new JButton();
        securityBar = new JProgressBar();
        jLabel1 = new JLabel("Nivel de seguridad");

        VER.setIcon(new ImageIcon(getClass().getResource("/imagenesComponente/ver_32px.png")));
        VER.setText("Ver");
        VER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VERMouseClicked(evt);
            }
        });

        OCULTAR.setIcon(new ImageIcon(getClass().getResource("/imagenesComponente/ocultar_32px.png")));
        OCULTAR.setText("Ocul");
        OCULTAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OCULTARMouseClicked(evt);
            }
        });

        jButton1.setIcon(new ImageIcon(getClass().getResource("/imagenesComponente/generatekeysarrows_generar_llave_1490.png")));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        // Diseño del panel interno
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(securityBar, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(OCULTAR, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(VER, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(VER)
                            .addComponent(OCULTAR)
                            .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(securityBar, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12))
        );

        this.setLayout(new java.awt.BorderLayout());
        this.add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    private void VERMouseClicked(java.awt.event.MouseEvent evt) {
        VER.setVisible(false);
        OCULTAR.setVisible(true);
        passwordField.setEchoChar((char) 0);
    }

    private void OCULTARMouseClicked(java.awt.event.MouseEvent evt) {
        VER.setVisible(true);
        OCULTAR.setVisible(false);
        passwordField.setEchoChar('●');
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        String generatedPassword = Generarcontraseña(14);
        passwordField.setText(generatedPassword);
        int securityLevel = Calculabarraseguridad(generatedPassword);
        securityBar.setValue(securityLevel);
    }

    public static String Generarcontraseña(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }

        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = "abcdefghijklmnopqrstuvwxyz";
        final String digits = "0123456789";
        final String special = "!@#$%^&*()";
        final String allChars = upper + lower + digits + special;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        for (int i = 4; i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int j = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }

        return new String(passwordArray);
    }

    public static int Calculabarraseguridad(String password) {
        int score = 0;
        if (password.length() >= 8) score += 25;
        if (password.length() > 8) score += 10;
        if (Pattern.compile("[A-Z]").matcher(password).find()) score += 25;
        if (Pattern.compile("[0-9]").matcher(password).find()) score += 25;
        if (Pattern.compile("[!@#$%^&*()]").matcher(password).find()) score += 25;
        return score;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Panel de Contraseña");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setContentPane(new BotonesC());
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
