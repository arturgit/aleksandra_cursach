package test;

import models.Option;
import models.Question;
import models.Test;
import test.listeners.SelectOptionListener;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Artur on 04.12.2017.
 */
public class QuestionPanel extends JPanel {
    private Test test = null;
    private Question currentQuestion = null;
    private int currentQuestionIndex = 0;
    private int rightResponses = 0;

    private JLabel titleLabel = null;
    private JLabel questionLabel = null;
    private JRadioButton firstButton = null;
    private JRadioButton secondButton = null;
    private JRadioButton therdButton = null;
    private ButtonGroup bG = null;
    private JButton selectButton = null;

    public QuestionPanel(Test test) {
        this.test = test;
        this.currentQuestionIndex = 0;
        this.currentQuestion = test.getQuestions().get(this.currentQuestionIndex);
        this.initComponents();
    }

    public int getRightResponses() {
        return this.rightResponses;
    }

    public int getTestId() {
        return this.test.getId();
    }

    public boolean nextQuestion() {
        this.currentQuestionIndex++;
        if (this.test.getQuestions().size() > this.currentQuestionIndex) {
            this.currentQuestion = this.test.getQuestions().get(this.currentQuestionIndex);
            this.updateComponents();
            return true;
        } else {
            return false;
        }
    }

    private void initComponents() {
        this.setLayout(new GridLayout(6,1));

        this.titleLabel = new JLabel(this.test.getTitle());
        this.add(this.titleLabel);

        this.questionLabel = new JLabel(this.currentQuestion.getTitle());
        this.add(this.questionLabel);

        this.firstButton = new JRadioButton("1");
        this.secondButton = new JRadioButton("2");
        this.therdButton = new JRadioButton("3");
        this.bG = new ButtonGroup();
        this.bG.add(firstButton);
        this.bG.add(secondButton);
        this.bG.add(therdButton);
        this.setSize(100,150);
        this.add(firstButton);
        this.add(secondButton);
        this.add(therdButton);

        this.firstButton.setSelected(true);

        this.selectButton = new JButton("Ответить");
        this.selectButton.addActionListener(new SelectOptionListener(this));
        this.add(this.selectButton);

        this.updateComponents();
    }

    private void updateComponents() {
        this.questionLabel.setText(this.currentQuestion.getTitle());
        List<Option> options = this.currentQuestion.getOptions();
        this.firstButton.setText(options.get(0).getTitle());
        this.secondButton.setText(options.get(1).getTitle());
        this.therdButton.setText(options.get(2).getTitle());
    }

    public void checkRightResponse() {
        if (this.currentQuestion.getOptions().get(this.getCheckedIndex()).isIstrue()) {
            this.rightResponses++;
        }
    }

    private int getCheckedIndex() {
        if (this.firstButton.isSelected()) return 0;
        if (this.secondButton.isSelected()) return 1;
        return 2;
    }
}
