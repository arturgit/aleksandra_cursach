package result.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Artur on 15.12.2017.
 */
public class SaveInFileListener implements ActionListener {
    private static final String savePath = ".\\result.txt";

    private int col = 0;
    private int result = 0;

    public SaveInFileListener(int col, int result) {
        this.col = col;
        this.result = result;
    }

    public void actionPerformed(ActionEvent e) {
        try(FileWriter writer = new FileWriter(savePath, false))
        {
            String text = "Вы ответили на "+this.result+" вопросов из "+this.col;
            text += ". Выполнено " + ((double)this.result)/this.col*100 + "% теста. ";
            if (((double)this.result)/this.col > 0.5) {
                text += " Тест сдан успешно.";
            } else {
                text += " Тест не сдан.";
            }
            writer.write(text);
            writer.flush();
            JOptionPane.showMessageDialog(new JOptionPane(), "Сохранено в " + savePath);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
