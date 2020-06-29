import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Textbook {
    JMenuBar jMenuBar;
    JMenu menu;
    JMenuItem[] items;
    JSeparator jSeparator;
    JFrame frame;
    JTextArea jTextArea;
    JScrollPane jScrollPane;
    private static String[] name = {"打开", "保存", "另存为", "退出"} ;

    public void go() {
        init();
        add();
        MyListener listener = new MyListener(this);
        addListener(listener);
    }

    private void init() {
        initFrame();
        initMenu();
        initText();
    }

    private void initFrame() {
        frame = new JFrame("textbook");
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initMenu() {
        jMenuBar = new JMenuBar();
        menu = new JMenu("文件");
        items = new JMenuItem[4];
        for (int i = 0; i < 4; i++){
            items[i] = new JMenuItem(name[i]);
        }
        jSeparator = new JSeparator();
    }

    private void initText() {
        jTextArea = new JTextArea();
        jTextArea.setSize(450,400);
        jTextArea.setRows(18);
        jTextArea.setLineWrap(true);
        jTextArea.setFont(new Font("宋体",0,20));
        jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setViewportView(jTextArea);
    }

    private void add() {
        addMenu();
        addText();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    private void addMenu() {
        jMenuBar.add(menu);
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                menu.add(jSeparator);
            }
            menu.add(items[i]);
        }
        frame.setJMenuBar(jMenuBar);
    }

    private void addText() {
        frame.add(jScrollPane);
    }

    private void addListener(MyListener listener) {
        for (int i = 0; i < 4; i++) {
            items[i].addActionListener(listener);
        }
    }
}
