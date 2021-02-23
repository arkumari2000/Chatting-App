package src.chatting.application;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends JFrame implements ActionListener{

    JPanel header;
    JTextField messageArea;
    JButton sendButton;
    static JTextArea messageDisplay;
    static Socket skt;
    static DataInputStream din;
    static DataOutputStream dout;

    Client(){
        // Header Code
        header = new JPanel();
        header.setLayout(null);
        header.setBounds(0,0,450,70);
        header.setBackground(new Color(71,147,215));
        add(header);

        // Arrow Icon code
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("src/chatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label1 = new JLabel(i3);
        label1.setBounds(5, 17, 30, 30);
        header.add(label1);

        label1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        // DP code
        ImageIcon dp1 = new ImageIcon(ClassLoader.getSystemResource("src/chatting/application/icons/bhati.png"));
        Image dp2 = dp1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon dp3 = new ImageIcon(dp2);
        JLabel label2 = new JLabel(dp3);
        label2.setBounds(40, 5, 60, 60);
        header.add(label2);

        // Name Code
        JLabel label3 = new JLabel("Bhati");
        label3.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        label3.setForeground(Color.WHITE);
        label3.setBounds(110,15,100,18);
        header.add(label3);

        // Active Status
        JLabel label4 = new JLabel("Active Now");
        label4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        label4.setBounds(110,35,100,20);
        header.add(label4);

        // Video Icon
        ImageIcon video1 = new ImageIcon(ClassLoader.getSystemResource("src/chatting/application/icons/video.png"));
        Image video2 = video1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon video3 = new ImageIcon(video2);
        JLabel label5 = new JLabel(video3);
        label5.setBounds(330, 17, 30, 30);
        header.add(label5);

        // Phone Icon
        ImageIcon phone1 = new ImageIcon(ClassLoader.getSystemResource("src/chatting/application/icons/phone.png"));
        Image phone2 = phone1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon phone3 = new ImageIcon(phone2);
        JLabel label6 = new JLabel(phone3);
        label6.setBounds(380, 17, 30, 30);
        header.add(label6);

        // Dots Icon
        ImageIcon dots1 = new ImageIcon(ClassLoader.getSystemResource("src/chatting/application/icons/3icon.png"));
        Image dots2 = dots1.getImage().getScaledInstance(15, 30, Image.SCALE_DEFAULT);
        ImageIcon dots3 = new ImageIcon(dots2);
        JLabel label7 = new JLabel(dots3);
        label7.setBounds(420, 17, 15, 30);
        header.add(label7);

        // Message Display Window Code
        messageDisplay = new JTextArea();
        messageDisplay.setBounds(5, 74, 440, 475);
        messageDisplay.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        messageDisplay.setEditable(false);
        messageDisplay.setLineWrap(true);
        messageDisplay.setWrapStyleWord(true);
        add(messageDisplay);

        // Text Area Code
        messageArea = new JTextField();
        messageArea.setBounds(3, 552, 310, 44);
        messageArea.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(messageArea);

        // Send Button Code
        sendButton = new JButton("Send");
        sendButton.setBounds(320, 555, 123, 38);
        sendButton.setBackground(new Color(71,147,215));
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);
        sendButton.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(this);
        add(sendButton);

        setLayout(null);
        setSize(450,600);
        setLocation(800,200);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            String out = messageArea.getText();
            messageDisplay.setText(messageDisplay.getText()+"\n\t\t\t"+out);
            dout.writeUTF(out);
            messageArea.setText("");
        }catch(Exception e){}
        
    }
    public static void main(String[] args){
        new Client().setVisible(true);

        String messageInput = "";
        try{
            skt = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(skt.getInputStream());
            dout = new DataOutputStream(skt.getOutputStream());

            messageInput = din.readUTF();
            messageDisplay.setText(messageDisplay.getText()+"\n"+messageInput);
        }catch(Exception e){}
    }
}
