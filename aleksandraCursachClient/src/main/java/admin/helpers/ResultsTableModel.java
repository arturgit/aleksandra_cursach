package admin.helpers;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Artur on 09.12.2017.
 */
public class ResultsTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<TableResult> results;

    public ResultsTableModel(List<TableResult> results) {
        this.results = results;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 6;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "id";
            case 1: return "login";
            case 2: return "level";
            case 3: return "position";
            case 4: return "colQuestions";
            case 5: return "rightQuestions";
        }
        return "";
    }

    public int getRowCount() {
        return results.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        TableResult result = results.get(rowIndex);
        switch (columnIndex) {
            case 0: return result.getId();
            case 1: return result.getLogin();
            case 2: return result.getLevel();
            case 3: return result.getPosition();
            case 4: return result.getColQuestions();
            case 5: return result.getRightQuestions();
        }
        return "";
    }

    public TableResult getRow(int rowIndex) {
        return results.get(rowIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
