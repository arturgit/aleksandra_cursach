package admin.panels;

import admin.helpers.ResultsTableModel;
import admin.helpers.TableResult;
import models.Result;
import remote.ClientConnector;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artur on 09.12.2017.
 */
public class ResultsPanel extends JPanel {
    private JTable table = null;
    private ResultsTableModel tableModel = null;
    private JLabel titleLabel = null;

    public ResultsPanel() {
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.createComponents();
        this.initJTable();
        this.initTopPanel();
    }

    private void createComponents() {
        this.table = new JTable();
        this.titleLabel = new JLabel("Результаты: ");
    }

    private void initTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(this.titleLabel);
        this.add(panel, BorderLayout.PAGE_START);
    }

    private void initJTable() {
        this.add(this.getTable(), BorderLayout.CENTER);
    }

    private JScrollPane getTable() {
        this.tableModel = new ResultsTableModel(this.getTableResultsFromServer());
        this.table = new JTable(this.tableModel);
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this.tableModel);
        table.setRowSorter(sorter);
        return new JScrollPane(table);
    }

    private List<TableResult> getTableResultsFromServer() {
        List<Result> results = null;
        try {
            results = ClientConnector.getAdminRemote().getResults();
        } catch (RemoteException e) {
            e.printStackTrace();
            results = new ArrayList<Result>();
        }
        return this.transformResultsToTableResults(results);
    }

    private List<TableResult> transformResultsToTableResults(List<Result> results) {
        List<TableResult> tableResults = new LinkedList<TableResult>();
        for (Result result: results) {
            tableResults.add(new TableResult(result));
        }
        return tableResults;
    }
}

