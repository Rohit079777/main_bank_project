package bankManagementSystem;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class AdminDashboard extends JFrame implements ActionListener {
    JTable table;
    JTextField searchField, updateField, deleteField, loanFormField, investmentFormField, formnoField;
    JButton viewBtn, searchBtn, updateBtn, deleteBtn, approveLoanBtn, rejectLoanBtn, approveInvestmentBtn,
            rejectInvestmentBtn;
    JButton markResolvedBtn, deleteComplaintBtn;
    JComboBox<String> tableSelector, fieldSelector;

    AdminDashboard() {
        setTitle("Admin Dashboard");
        setLayout(null);

        JLabel heading = new JLabel("Admin Panel - Manage Bank Database");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(150, 10, 400, 30);
        add(heading);

        JLabel selectTableLabel = new JLabel("Select Table:");
        selectTableLabel.setBounds(20, 50, 100, 25);
        add(selectTableLabel);

        tableSelector = new JComboBox<>(new String[] {
                "rg1", "rg2", "rg3", "login", "account_balance", "investments",
                "loans", "payments", "support_requests", "transactions", "services"
        });
        tableSelector.setBounds(130, 50, 150, 25);
        add(tableSelector);

        viewBtn = new JButton("View Data");
        viewBtn.setBounds(300, 50, 120, 25);
        viewBtn.addActionListener(this);
        add(viewBtn);

        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 100, 700, 250);
        add(sp);

        JLabel searchLabel = new JLabel("Search by formno:");
        searchLabel.setBounds(20, 370, 120, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(150, 370, 150, 25);
        add(searchField);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(320, 370, 100, 25);
        searchBtn.addActionListener(this);
        add(searchBtn);

        JLabel formnoLabel = new JLabel("Enter a Form Number To Update :");
        formnoLabel.setBounds(20, 410, 250, 25);
        add(formnoLabel);

        formnoField = new JTextField();
        formnoField.setBounds(220, 410, 150, 25);
        add(formnoField);

        JLabel updateLabel = new JLabel("Update Field:");
        updateLabel.setBounds(20, 450, 100, 25);
        add(updateLabel);

        String[] rg1_rg2_fields = {
            "name", "fname", "dob", "gender", "email", "marital", "add1", "city", "state", "pincode",
            "religion", "category", "income", "education", "occupation", "pan", "adhar", "seniorCitizen", "existingAccount"
        };
        fieldSelector = new JComboBox<>(rg1_rg2_fields);
        fieldSelector.setBounds(130, 450, 150, 25);
        add(fieldSelector);

        JLabel newValueLabel = new JLabel("New Value:");
        newValueLabel.setBounds(20, 490, 100, 25);
        add(newValueLabel);

        updateField = new JTextField();
        updateField.setBounds(130, 490, 150, 25);
        add(updateField);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(300, 490, 100, 25);
        updateBtn.addActionListener(this);
        add(updateBtn);

        JLabel deleteLabel = new JLabel("Delete by formno:");
        deleteLabel.setBounds(20, 530, 120, 25);
        add(deleteLabel);

        deleteField = new JTextField();
        deleteField.setBounds(150, 530, 150, 25);
        add(deleteField);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(320, 530, 100, 25);
        deleteBtn.addActionListener(this);
        add(deleteBtn);

        JLabel loanLabel = new JLabel("Loan Approval (PIN Number):");
        loanLabel.setBounds(20, 570, 200, 25);
        add(loanLabel);

        loanFormField = new JTextField();
        loanFormField.setBounds(220, 570, 150, 25);
        add(loanFormField);

        approveLoanBtn = new JButton("Approve Loan");
        approveLoanBtn.setBounds(380, 570, 120, 25);
        approveLoanBtn.addActionListener(this);
        add(approveLoanBtn);

        rejectLoanBtn = new JButton("Reject Loan");
        rejectLoanBtn.setBounds(510, 570, 120, 25);
        rejectLoanBtn.addActionListener(this);
        add(rejectLoanBtn);

        JLabel investmentLabel = new JLabel("Investment Approval (Form Number):");
        investmentLabel.setBounds(20, 610, 250, 25);
        add(investmentLabel);

        investmentFormField = new JTextField();
        investmentFormField.setBounds(270, 610, 150, 25);
        add(investmentFormField);

        approveInvestmentBtn = new JButton("Approve Investment");
        approveInvestmentBtn.setBounds(430, 610, 150, 25);
        approveInvestmentBtn.addActionListener(this);
        add(approveInvestmentBtn);

        rejectInvestmentBtn = new JButton("Reject Investment");
        rejectInvestmentBtn.setBounds(590, 610, 150, 25);
        rejectInvestmentBtn.addActionListener(this);
        add(rejectInvestmentBtn);

        // Customer Support Buttons
        markResolvedBtn = new JButton("Mark as Resolved");
        markResolvedBtn.setBounds(20, 650, 150, 25);
        markResolvedBtn.addActionListener(this);
        add(markResolvedBtn);

        deleteComplaintBtn = new JButton("Delete Complaint");
        deleteComplaintBtn.setBounds(180, 650, 150, 25);
        deleteComplaintBtn.addActionListener(this);
        add(deleteComplaintBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(650, 650, 80, 25);
        closeBtn.addActionListener(e -> System.exit(0));
        add(closeBtn);

        getContentPane().setBackground(new Color(255, 218, 185)); // Peach color
        setSize(800, 750);
        setResizable(false);
        setVisible(true);
        setLocation(350, 30);
    }

    public void actionPerformed(ActionEvent ae) {
        Conn conn = new Conn();
        String selectedTable = (String) tableSelector.getSelectedItem();

        if (ae.getSource() == viewBtn) {
            try {
                String query = "SELECT * FROM " + selectedTable;
                ResultSet rs = conn.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));

                
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 

                TableColumnModel columnModel = table.getColumnModel();
                for (int i = 0; i < columnModel.getColumnCount(); i++) {
                    columnModel.getColumn(i).setPreferredWidth(150); 
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        } else if (ae.getSource() == searchBtn) {
            String formno = searchField.getText();
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter formno to search.");
                return;
            }
            try {
                String query = "SELECT * FROM " + selectedTable + " WHERE formno = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, formno);
                ResultSet rs = ps.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == updateBtn) {
            String formno = formnoField.getText();
            String newValue = updateField.getText();
            String selectedField = (String) fieldSelector.getSelectedItem();
            if (formno.isEmpty() || newValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter form number and new value.");
                return;
            }
            try {
                String query = "UPDATE rg1 SET " + selectedField + " = ? WHERE formno = ?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, newValue);
                ps.setString(2, formno);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Updated Successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } 
        
        else if (ae.getSource() == deleteBtn) {
            String formno = deleteField.getText();
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter form number to delete.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    conn.c.setAutoCommit(false); // Start transaction

                    String[] tables = {"rg1", "rg2", "rg3", "login", "account_balance", "investments", "loans", "payments", "support_requests", "transactions", "services"};
                    int totalRowsAffected = 0;

                    for (String table : tables) {
                        // Check if the column 'formno' exists in the table
                        DatabaseMetaData meta = conn.c.getMetaData();
                        ResultSet rs = meta.getColumns(null, null, table, "formno");
                        if (rs.next()) {
                            String query = "DELETE FROM " + table + " WHERE formno = ?";
                            PreparedStatement ps = conn.c.prepareStatement(query);
                            ps.setString(1, formno);
                            totalRowsAffected += ps.executeUpdate();
                        }
                    }

                    conn.c.commit(); // Commit transaction

                    if (totalRowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Data Deleted Successfully from all tables.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Form number not found in any table.");
                    }
                } catch (Exception e) {
                    try {
                        conn.c.rollback(); // Rollback transaction in case of error
                    } catch (SQLException rollbackEx) {
                        JOptionPane.showMessageDialog(this, "Error during rollback: " + rollbackEx.getMessage());
                    }
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                } finally {
                    try {
                        conn.c.setAutoCommit(true); // Reset auto-commit to true
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error resetting auto-commit: " + ex.getMessage());
                    }
                }
            }
        } else if (ae.getSource() == approveLoanBtn) {
            String pinnum = loanFormField.getText();
            if (pinnum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter PIN number for approval.");
                return;
            }
            try {
                String checkQuery = "SELECT status FROM loans WHERE pinnum = ?";
                PreparedStatement psCheck = conn.c.prepareStatement(checkQuery);
                psCheck.setString(1, pinnum);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next() && "Pending".equalsIgnoreCase(rs.getString("status"))) {
                    String approveQuery = "UPDATE loans SET status = 'Approved' WHERE pinnum = ?";
                    PreparedStatement psApprove = conn.c.prepareStatement(approveQuery);
                    psApprove.setString(1, pinnum);
                    psApprove.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Loan Approved Successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Loan already processed or not found.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == rejectLoanBtn) {
            String pinnum = loanFormField.getText();
            if (pinnum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter PIN number for rejection.");
                return;
            }
            try {
                String checkQuery = "SELECT status FROM loans WHERE pinnum = ?";
                PreparedStatement psCheck = conn.c.prepareStatement(checkQuery);
                psCheck.setString(1, pinnum);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("status");

                    if ("Pending".equalsIgnoreCase(status)) {
                        String rejectQuery = "UPDATE loans SET status = 'Rejected' WHERE pinnum = ?";
                        PreparedStatement psReject = conn.c.prepareStatement(rejectQuery);
                        psReject.setString(1, pinnum);
                        psReject.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Loan Rejected Successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Loan already processed: " + status);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Loan not found for PIN: " + pinnum);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == approveInvestmentBtn) {
            String formno = investmentFormField.getText();
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter form number for approval.");
                return;
            }
            try {
                String checkQuery = "SELECT status FROM investments WHERE formno = ?";
                PreparedStatement psCheck = conn.c.prepareStatement(checkQuery);
                psCheck.setString(1, formno);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next() && "Pending".equalsIgnoreCase(rs.getString("status"))) {
                    String approveQuery = "UPDATE investments SET status = 'Approved' WHERE formno = ?";
                    PreparedStatement psApprove = conn.c.prepareStatement(approveQuery);
                    psApprove.setString(1, formno);
                    psApprove.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Investment Approved Successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Investment already processed or not found.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == rejectInvestmentBtn) {
            String formno = investmentFormField.getText();
            if (formno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter form number for rejection.");
                return;
            }
            try {
                String checkQuery = "SELECT status FROM investments WHERE formno = ?";
                PreparedStatement psCheck = conn.c.prepareStatement(checkQuery);
                psCheck.setString(1, formno);
                ResultSet rs = psCheck.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("status");

                    if ("Pending".equalsIgnoreCase(status)) {
                        String rejectQuery = "UPDATE investments SET status = 'Rejected' WHERE formno = ?";
                        PreparedStatement psReject = conn.c.prepareStatement(rejectQuery);
                        psReject.setString(1, formno);
                        psReject.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Investment Rejected Successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Investment already processed: " + status);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Investment not found for form number: " + formno);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == markResolvedBtn) {
            CustomerSupportService.markAsResolved(this);
        } else if (ae.getSource() == deleteComplaintBtn) {
            CustomerSupportService.deleteComplaint(this);
        }
    }

    public static void main(String[] args) {
        new AdminDashboard();
    }
}