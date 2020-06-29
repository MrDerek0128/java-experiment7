import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyListener implements ActionListener {
    private Textbook textbook;
    private String path;

    public MyListener(Textbook textbook) {
        this.textbook = textbook;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textbook.items[0]) {
            open();
        }

        else if (e.getSource() == textbook.items[1]) {
            save();
        }

        else if (e.getSource() == textbook.items[2]) {
            saveTo();
        }

        else if (e.getSource() == textbook.items[3]) {
            exit();
        }
    }

    private void open() {
        String s;
        FileDialog fileDialog = new FileDialog(textbook.frame);
        fileDialog.setVisible(true);
        path = fileDialog.getDirectory() + fileDialog.getFile();
        if (fileDialog.getFile() == null) {
            System.out.println("exit");
            return;
        }
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            String encoding = fileReader.getEncoding();
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),encoding);
            BufferedReader br = new BufferedReader(inputStreamReader);
            textbook.jTextArea.setText("");
            while ((s = br.readLine()) != null) {
                textbook.jTextArea.append(s);
            }
            fileReader.close();
            inputStreamReader.close();
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void save() {
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(textbook.jTextArea.getText(), 0, (textbook.jTextArea.getText()).length());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                saveTo();
            }
        }
    }

    private void saveTo() {
        FileDialog fileDialog = new FileDialog(textbook.frame);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() == null) {
            System.out.println("exit");
            return;
        }
        path = fileDialog.getDirectory() + fileDialog.getFile();
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(textbook.jTextArea.getText(), 0, (textbook.jTextArea.getText()).length());
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        File file = new File(path);
        String text = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
            String str = stringBuilder.toString();
            if (str.equals(textbook.jTextArea.getText())) {
                System.exit(0);
            } else {
                int n = JOptionPane.showConfirmDialog(null, "是否保存？", "保存", JOptionPane.YES_NO_OPTION);
                if (n == 1) {
                    System.exit(0);
                }
                else if (n == 0) {
                    save();
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
