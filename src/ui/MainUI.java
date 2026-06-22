package ui;

import service.AuthService;
import service.CryptoService;
import service.AdminService;
import service.HistoryService;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainUI extends JFrame {

    private JTabbedPane tabs;

    private JTextArea adminUsers;

    private JTextArea historyArea;

    private JLabel adminInfo;

    public MainUI() {

        try {

            for (
                    UIManager.LookAndFeelInfo info
                    :
                    UIManager.getInstalledLookAndFeels()
            ) {

                if (
                        "Nimbus".equals(
                                info.getName()
                        )
                ) {

                    UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        UIManager.put(
                "Button.font",
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        UIManager.put(
                "Label.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        UIManager.put(
                "TextField.font",
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        UIManager.put(
                "TabbedPane.font",
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        setTitle("Crypto System");

        setSize(950,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabs=new JTabbedPane();

        tabs.add(
                "Login",
                createLogin()
        );

        tabs.add(
                "Register",
                createRegister()
        );

        tabs.add(
                "User",
                createUser()
        );

        tabs.add(
                "Admin",
                createAdmin()
        );

        tabs.add(
                "History",
                createHistory()
        );

        tabs.setEnabledAt(
                2,
                false
        );

        tabs.setEnabledAt(
                3,
                false
        );

        tabs.setEnabledAt(
                4,
                false
        );

        add(tabs);

        tabs.addChangeListener(

                e->{

                    if(
                            tabs.getSelectedIndex()
                                    ==
                                    3
                    ){

                        loadAdmin();

                    }

                    if(
                            tabs.getSelectedIndex()
                                    ==
                                    4
                    ){

                        loadHistory();

                    }

                }

        );

        setVisible(true);

    }

    private JPanel panel(){

        JPanel p=
                new JPanel();

        p.setLayout(null);

        return p;

    }

    private JPanel createLogin(){

        JPanel p=
                panel();

        JLabel l1=
                new JLabel(
                        "Username"
                );

        JLabel l2=
                new JLabel(
                        "Password"
                );

        JTextField user=
                new JTextField();

        JPasswordField pass=
                new JPasswordField();

        JButton login=
                new JButton(
                        "Login"
                );

        JProgressBar loader=
                new JProgressBar();

        loader.setIndeterminate(
                true
        );

        loader.setVisible(
                false
        );

        l1.setBounds(
                260,
                150,
                120,
                30
        );

        user.setBounds(
                360,
                150,
                250,
                40
        );

        l2.setBounds(
                260,
                230,
                120,
                30
        );

        pass.setBounds(
                360,
                230,
                250,
                40
        );

        login.setBounds(
                390,
                320,
                180,
                45
        );

        loader.setBounds(
                340,
                390,
                250,
                10
        );

        p.add(l1);

        p.add(user);

        p.add(l2);

        p.add(pass);

        p.add(login);

        p.add(loader);

        login.addActionListener(e->{

            loader.setVisible(true);

            String role=

                    AuthService.login(

                            user.getText(),

                            new String(
                                    pass.getPassword()
                            )

                    );

            loader.setVisible(false);

            if(role.equals("USER")){

                tabs.setEnabledAt(2,true);

                tabs.setEnabledAt(4,true);

                tabs.setSelectedIndex(2);

            }

            else if(role.equals("ADMIN")){

                tabs.setEnabledAt(3,true);

                tabs.setEnabledAt(4,true);

                tabs.setSelectedIndex(3);

                loadAdmin();

            }

            else{

                JOptionPane.showMessageDialog(

                        null,

                        "Invalid Login"

                );

            }

        });

        return p;

    }

    private JPanel createRegister(){

        JPanel p=
                panel();

        JTextField user=
                new JTextField();

        JPasswordField pass=
                new JPasswordField();

        JButton register=
                new JButton(
                        "Register"
                );

        user.setBounds(
                340,
                180,
                250,
                40
        );

        pass.setBounds(
                340,
                260,
                250,
                40
        );

        register.setBounds(
                390,
                350,
                180,
                45
        );

        p.add(user);

        p.add(pass);

        p.add(register);

        register.addActionListener(e->{

            boolean ok=

                    AuthService.register(

                            user.getText(),

                            new String(
                                    pass.getPassword()
                            )

                    );

            JOptionPane.showMessageDialog(

                    null,

                    ok
                            ?
                            "Registered"
                            :
                            "Registration Failed"

            );

            if(ok){

                tabs.setSelectedIndex(0);

            }

        });

        return p;

    }

    private JPanel createUser(){

        JPanel p=
                panel();

        JButton encrypt=
                new JButton(
                        "Encrypt File"
                );

        JButton decrypt=
                new JButton(
                        "Decrypt File"
                );

        JButton logout=
                new JButton(
                        "Logout"
                );

        JLabel status=
                new JLabel();

        encrypt.setBounds(
                220,
                200,
                180,
                55
        );

        decrypt.setBounds(
                500,
                200,
                180,
                55
        );

        logout.setBounds(
                370,
                330,
                180,
                50
        );

        status.setBounds(
                250,
                450,
                600,
                40
        );

        p.add(encrypt);

        p.add(decrypt);

        p.add(logout);

        p.add(status);

        encrypt.addActionListener(e->{

            JFileChooser fc=
                    new JFileChooser();

            int r=
                    fc.showOpenDialog(
                            null
                    );

            if(
                    r
                            ==
                            JFileChooser.APPROVE_OPTION
            ){

                File file=
                        fc.getSelectedFile();

                String pass=

                        JOptionPane.showInputDialog(

                                this,

                                "Enter Encryption Password"

                        );

                if(pass!=null){

                    String msg=

                            CryptoService.encrypt(

                                    file.getAbsolutePath(),

                                    pass

                            );

                    status.setText(msg);

                }

            }

        });

        decrypt.addActionListener(e->{

            JFileChooser fc=
                    new JFileChooser();

            int r=
                    fc.showOpenDialog(
                            null
                    );

            if(
                    r
                            ==
                            JFileChooser.APPROVE_OPTION
            ){

                File file=
                        fc.getSelectedFile();

                String pass=

                        JOptionPane.showInputDialog(

                                this,

                                "Enter Decryption Password"

                        );

                if(pass!=null){

                    String msg=

                            CryptoService.decryptByPath(

                                    file.getAbsolutePath(),

                                    pass

                            );

                    status.setText(msg);

                }

            }

        });

        logout.addActionListener(e->{

            tabs.setSelectedIndex(0);

            tabs.setEnabledAt(2,false);

            tabs.setEnabledAt(3,false);

            tabs.setEnabledAt(4,false);

        });

        return p;

    }

    private JPanel createAdmin(){

        JPanel p=
                panel();

        adminInfo=
                new JLabel();

        adminUsers=
                new JTextArea();

        JScrollPane scroll=
                new JScrollPane(
                        adminUsers
                );

        JButton refresh=
                new JButton(
                        "Refresh"
                );

        scroll.setBounds(
                70,
                100,
                800,
                350
        );

        refresh.setBounds(
                390,
                500,
                160,
                45
        );

        adminInfo.setBounds(
                70,
                40,
                700,
                30
        );

        p.add(adminInfo);

        p.add(scroll);

        p.add(refresh);

        refresh.addActionListener(
                e->loadAdmin()
        );

        return p;

    }

    private JPanel createHistory(){

        JPanel p=
                panel();

        historyArea=
                new JTextArea();

        JScrollPane scroll=
                new JScrollPane(
                        historyArea
                );

        scroll.setBounds(
                70,
                70,
                800,
                430
        );

        p.add(scroll);

        return p;

    }

    private void loadAdmin(){

        adminUsers.setText(

                AdminService.getUsers()

        );

        adminInfo.setText(

                "Users : "

                        +

                        AdminService.totalUsers()

        );

    }

    private void loadHistory(){

        historyArea.setText(

                HistoryService.getHistory()

        );

    }

}
