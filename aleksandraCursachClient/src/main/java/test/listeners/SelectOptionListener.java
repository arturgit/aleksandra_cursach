package test.listeners;

import router.MainRouter;
import test.QuestionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Artur on 04.12.2017.
 */
public class SelectOptionListener implements ActionListener {
    private QuestionPanel questionPanel = null;

    public SelectOptionListener(QuestionPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public void actionPerformed(ActionEvent e) {
        this.questionPanel.checkRightResponse();
        if (!this.questionPanel.nextQuestion()) {
            MainRouter.getMainRouter().resultRoute(
                    this.questionPanel.getTestId(),
                    this.questionPanel.getRightResponses());
        }
    }
}
