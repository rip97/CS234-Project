/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.bankaccount;

import javax.swing.JOptionPane;

/**
 *
 * @author alyazzie
 */
public class CreateAccountTypeGUI extends javax.swing.JDialog {
    
    int holderID; 
    BankAccount account;
    /**
     * @param holderID
     * Creates new form CreateAccountGUI
     */
    public CreateAccountTypeGUI(int holderID){
        super((java.awt.Frame) null, true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.holderID = holderID;
        initComponents();
    }
       
    public BankAccount getAccount()
    {
        return account;
    }
    
    public void close()
    {
        dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroupAccountTypes = new javax.swing.ButtonGroup();
        lblAccountTypes = new javax.swing.JLabel();
        lblCheckingAccounts = new javax.swing.JLabel();
        lblSavingsAccounts = new javax.swing.JLabel();
        lblIRAAccounts = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        lblCheckingInfoToolTip = new javax.swing.JLabel();
        lblSavingsInfoToolTip = new javax.swing.JLabel();
        lblIRAInfoToolTip = new javax.swing.JLabel();
        btnBasicChecking = new javax.swing.JRadioButton();
        btnPremiumChecking = new javax.swing.JRadioButton();
        btnStandardSavings = new javax.swing.JRadioButton();
        btnHighYieldSavings = new javax.swing.JRadioButton();
        btnTraditionalIRA = new javax.swing.JRadioButton();
        btnRothIRA = new javax.swing.JRadioButton();

        setTitle("New Accounts");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAccountTypes.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblAccountTypes.setText("Which type of account would you like to open today?");
        getContentPane().add(lblAccountTypes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        lblCheckingAccounts.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblCheckingAccounts.setText("Checking Accounts");
        lblCheckingAccounts.setToolTipText("\n");
        getContentPane().add(lblCheckingAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        lblSavingsAccounts.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblSavingsAccounts.setText("Savings Accounts");
        getContentPane().add(lblSavingsAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        lblIRAAccounts.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblIRAAccounts.setText("IRA Accounts");
        getContentPane().add(lblIRAAccounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        btnSubmit.setText("Submit");
        btnSubmit.setSelected(true);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        lblCheckingInfoToolTip.setFont(new java.awt.Font("Lucida Grande", 1, 8)); // NOI18N
        lblCheckingInfoToolTip.setForeground(new java.awt.Color(0, 51, 255));
        lblCheckingInfoToolTip.setText("[Info]");
        lblCheckingInfoToolTip.setToolTipText("<html>\n<body>\n<p>\nBasic Checking Account\n</p>\n<p>\n======================\n</p>\n<p>\n- $300 Daily Withdrawal Limit\n</p>\n<p>\n- $1 Fee on all Transactions\n</p>\n<p>\nPremium Checking Account\n</p>\n<p>\n======================\n</p>\n<p>\n- $500 Daily Withdrawal Limit\n</p>\n<p>\n- No Transaction Fees\n</p>\n<p>\n*** Both Account Types have a $35 Overdraft Fee ***\n</body>\n</html>");
        getContentPane().add(lblCheckingInfoToolTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 30, 20));

        lblSavingsInfoToolTip.setFont(new java.awt.Font("Lucida Grande", 1, 8)); // NOI18N
        lblSavingsInfoToolTip.setForeground(new java.awt.Color(0, 51, 255));
        lblSavingsInfoToolTip.setText("[Info]");
        lblSavingsInfoToolTip.setToolTipText("<html>\n<body>\n<p>\nStandard Savings Account Info\n</p>\n<p>\n======================\n</p>\n<p>\n- $50 minimum deposit\n</p>\n<p>\n- 0.15% Annual Percentage Yield\n</p>\n<p>\n- Monthly withdrawal/transfer limit is 9 per month\n</p>\n<p>\n- $5.00 withdrawal fee\n</p>\n<p>\n</p>\n<p>\nHigh Yield Savings Account Info\n</p>\n<p>\n======================\n</p>\n<p>\n- $500 minimum deposit\n</p>\n<p>\n- 0.40% Annual Percentage Yield\n</p>\n<p>\n- Monthly withdrawal/transfer limit is 5 per month\n</p>\n<p>\n- $5.00 withdrawal fee\n</body>\n</html>");
        getContentPane().add(lblSavingsInfoToolTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, 20));

        lblIRAInfoToolTip.setFont(new java.awt.Font("Lucida Grande", 1, 8)); // NOI18N
        lblIRAInfoToolTip.setForeground(new java.awt.Color(0, 51, 255));
        lblIRAInfoToolTip.setText("[Info]");
        getContentPane().add(lblIRAInfoToolTip, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, 20));

        btngroupAccountTypes.add(btnBasicChecking);
        btnBasicChecking.setText("Basic");
        getContentPane().add(btnBasicChecking, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        btngroupAccountTypes.add(btnPremiumChecking);
        btnPremiumChecking.setText("Premium");
        getContentPane().add(btnPremiumChecking, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        btngroupAccountTypes.add(btnStandardSavings);
        btnStandardSavings.setText("Standard");
        getContentPane().add(btnStandardSavings, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        btngroupAccountTypes.add(btnHighYieldSavings);
        btnHighYieldSavings.setText("High Yield");
        getContentPane().add(btnHighYieldSavings, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        btngroupAccountTypes.add(btnTraditionalIRA);
        btnTraditionalIRA.setText("Traditional");
        getContentPane().add(btnTraditionalIRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        btngroupAccountTypes.add(btnRothIRA);
        btnRothIRA.setText("Roth");
        getContentPane().add(btnRothIRA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        setSize(new java.awt.Dimension(524, 413));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        int totalSelectionCount = 0;
        InitialDepositGUI initialDeposit;
        
        if (btnBasicChecking.isSelected())
        {  
            initialDeposit = new InitialDepositGUI(1);
            initialDeposit.setVisible(true);
            account = new Checking(initialDeposit.getInitDepo(),holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        }
        if (btnPremiumChecking.isSelected()) {
            initialDeposit = new InitialDepositGUI(2);
            initialDeposit.setVisible(true);
            account = new PremiumChecking(initialDeposit.getInitDepo(),holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        }
       
        if (btnStandardSavings.isSelected()) {
            
            initialDeposit = new InitialDepositGUI(3);
            initialDeposit.setVisible(true);
            account = new Savings(initialDeposit.getInitDepo(),holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        } 
        if (btnHighYieldSavings.isSelected()) {
            
            initialDeposit = new InitialDepositGUI(4);
            initialDeposit.setVisible(true);
            account = new HighYield(initialDeposit.getInitDepo(),holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        } 
        if (btnTraditionalIRA.isSelected()) {
            
            initialDeposit = new InitialDepositGUI(5);
            initialDeposit.setVisible(true);
            account = new Traditional(initialDeposit.getBDay(), initialDeposit.getTaxIn(),initialDeposit.getInitDepo(), holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        } 
        if (btnRothIRA.isSelected()) {
            
            initialDeposit = new InitialDepositGUI(6);
            initialDeposit.setVisible(true);
            account = new Roth(initialDeposit.getTaxIn(),initialDeposit.getBDay(), initialDeposit.getTaxIn(),initialDeposit.getInitDepo(),holderID);
            totalSelectionCount++;
            initialDeposit.dispose();
        }

        if (totalSelectionCount > 0) {
            //Hides Current Window
            setVisible(false); 
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "No account types selected. Please make a selection.");
        }
        
    }//GEN-LAST:event_btnSubmitActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnBasicChecking;
    private javax.swing.JRadioButton btnHighYieldSavings;
    private javax.swing.JRadioButton btnPremiumChecking;
    private javax.swing.JRadioButton btnRothIRA;
    private javax.swing.JRadioButton btnStandardSavings;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JRadioButton btnTraditionalIRA;
    private javax.swing.ButtonGroup btngroupAccountTypes;
    private javax.swing.JLabel lblAccountTypes;
    private javax.swing.JLabel lblCheckingAccounts;
    private javax.swing.JLabel lblCheckingInfoToolTip;
    private javax.swing.JLabel lblIRAAccounts;
    private javax.swing.JLabel lblIRAInfoToolTip;
    private javax.swing.JLabel lblSavingsAccounts;
    private javax.swing.JLabel lblSavingsInfoToolTip;
    // End of variables declaration//GEN-END:variables
}