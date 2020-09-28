package decoratorpagamentocartao;

import decoratorpagamentocartao.entity.CartaoCredito;
import decoratorpagamentocartao.entity.Cliente;
import decoratorpagamentocartao.entity.Pedido;
import decoratorpagamentocartao.service.AnalisadorAntiFraudeClearSale;
import decoratorpagamentocartao.service.AnalisadorAntiFraudeKonduto;
import decoratorpagamentocartao.service.GatewayCielo;
import decoratorpagamentocartao.service.GatewayPagamento;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.Year;
import java.time.YearMonth;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class Main extends javax.swing.JFrame {


    public Main() {
        initComponents();
        
        customizarLayout();
        
        Vector<Integer> meses = carregarMeses(12);
        jComboBoxMesVencimentoCartao.setModel(new DefaultComboBoxModel(meses));  
        
        Vector<Integer> anos = carregarAnos(Year.now().plusYears(new Long(30)).getValue());
        jComboBoxAnoVencimentoCartao.setModel(new DefaultComboBoxModel(anos));     
        
        preencherCamposTeste();    
        
        jTextFieldTotalVenda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jTextFieldTotalVenda.setText("");
            }
        });
        
    }

    private void customizarLayout() {
        getContentPane().setBackground(Color.WHITE);
        
        Color color = new Color(239,239,239);
        jPanelPagamentoCartao.setBackground(color);
        
        jButtonPagar.setBackground(Color.darkGray);
        jButtonPagar.setForeground(Color.white);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanelPagamentoCartao = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNumeroCartao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeCartao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxMesVencimentoCartao = new javax.swing.JComboBox<>();
        jComboBoxAnoVencimentoCartao = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodigoSeguranca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButtonPagar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCPF = new javax.swing.JTextField();
        jTextFieldTotalVenda = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Checkout Pagamento");

        jLabel4.setText("Número Cartão");

        jLabel3.setText("Nome Impresso no cartão");

        jLabel5.setText("Validade");

        jComboBoxMesVencimentoCartao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxAnoVencimentoCartao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("CVV");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Total");

        jButtonPagar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonPagar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPagar.setText("Pagar");
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        jLabel8.setText("CPF");

        jLabel7.setText("Mês");

        jLabel9.setText("Ano");

        javax.swing.GroupLayout jPanelPagamentoCartaoLayout = new javax.swing.GroupLayout(jPanelPagamentoCartao);
        jPanelPagamentoCartao.setLayout(jPanelPagamentoCartaoLayout);
        jPanelPagamentoCartaoLayout.setHorizontalGroup(
            jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagamentoCartaoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPagamentoCartaoLayout.createSequentialGroup()
                        .addComponent(jComboBoxMesVencimentoCartao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxAnoVencimentoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanelPagamentoCartaoLayout.createSequentialGroup()
                        .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldNumeroCartao, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                                        .addComponent(jTextFieldNomeCartao)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addComponent(jLabel8))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPagamentoCartaoLayout.createSequentialGroup()
                                    .addComponent(jTextFieldCodigoSeguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(119, 119, 119)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldTotalVenda)))
                            .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelPagamentoCartaoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(186, 186, 186))))
        );
        jPanelPagamentoCartaoLayout.setVerticalGroup(
            jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPagamentoCartaoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNumeroCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(1, 1, 1)
                .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxAnoVencimentoCartao)
                    .addComponent(jComboBoxMesVencimentoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPagamentoCartaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigoSeguranca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButtonPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jPanelPagamentoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel2)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanelPagamentoCartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
       
        try{
           String numeroCartao   = jTextFieldNumeroCartao.getText();
           String nomeCartao     = jTextFieldNomeCartao.getText();
           String cpfCliente     = jTextFieldCPF.getText();
           Integer mesVencimento = (Integer) jComboBoxMesVencimentoCartao.getSelectedItem();
           Integer anoVencimento = (Integer) jComboBoxAnoVencimentoCartao.getSelectedItem();
           String codSeguranca   = jTextFieldCodigoSeguranca.getText();
           String totalVenda     = jTextFieldTotalVenda.getText();
           Float valorTotalVenda = Float.parseFloat(totalVenda.equals("") ? "0.0" : totalVenda);

           Cliente cliente = new Cliente(new Random().nextInt(), nomeCartao, cpfCliente);

           CartaoCredito cartaoCredito = new CartaoCredito(numeroCartao, 
                                                           nomeCartao, 
                                                           YearMonth.of(anoVencimento, mesVencimento), 
                                                           Integer.parseInt(codSeguranca));
           
           GatewayPagamento gatewayAutorizador = new AnalisadorAntiFraudeClearSale(new GatewayCielo());
           
           gatewayAutorizador = new AnalisadorAntiFraudeKonduto(gatewayAutorizador);

           boolean autorizado = gatewayAutorizador.autorizar(new Pedido(new Random().nextInt(), valorTotalVenda, cliente, cartaoCredito ));
           
           if (autorizado) {
               JOptionPane.showMessageDialog(null, "Transação Autorizada!.");
           } else {
               JOptionPane.showMessageDialog(null, "Transação não Autorizada!.");
           }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButtonPagarActionPerformed


    private void preencherCamposTeste() {
        jTextFieldNumeroCartao.setText("4444 4444 4444 4444");
        jTextFieldNomeCartao.setText("Robert C Martin");
        jTextFieldCPF.setText("00535640029"); 
        jTextFieldCodigoSeguranca.setText("159");
        jTextFieldTotalVenda.setText("0.00");
    }    
    
    private Vector<Integer> carregarMeses(Integer contador) {
        
        Vector<Integer> vetor = new Vector();
        
        for(int i = 1; i <= contador; i++) {
            vetor.add(i);
        }
        
        return vetor;
    }    
    
    private Vector<Integer> carregarAnos(Integer anoLimite) {
        
        Vector<Integer> vetor = new Vector();
        
        for(int i = Year.now().getValue(); i <= anoLimite; i++) {
            vetor.add(i);
        }
        
        return vetor;
    }     
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JComboBox<String> jComboBoxAnoVencimentoCartao;
    private javax.swing.JComboBox<String> jComboBoxMesVencimentoCartao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelPagamentoCartao;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldCodigoSeguranca;
    private javax.swing.JTextField jTextFieldNomeCartao;
    private javax.swing.JTextField jTextFieldNumeroCartao;
    private javax.swing.JTextField jTextFieldTotalVenda;
    // End of variables declaration//GEN-END:variables


}
