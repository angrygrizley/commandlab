import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainWindow {
    private JPanel MainPanel;
    private JButton слушатьButton;
    private JTextArea textArea1;


    private ServerSocket server;
    private Socket client;
    BufferedReader in;
    PrintWriter out;


    public MainWindow() {

        слушатьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listenSocket();
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("App");
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setContentPane(new MainWindow().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public void listenSocket() {
        try {
            server = new ServerSocket(4321);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
        while (true) {
            ClientWorker w;
            try {
//server.accept returns a client connection
                w = new ClientWorker(server.accept(), textArea1);
                Thread t = new Thread(w);
                t.start();
            } catch (IOException e) {
                System.out.println("Accept failed: 4321");
                System.exit(-1);
            }
        }

    }
}