package decoratorpagamentocartao.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class UtilComponentes {
    
    
    /**
     * Limpar campos de JTextComponent
     * @param campos - campos para setar string vazia
    */
    public static void limparCampos(JTextComponent... campos) {
        String vazio = "";
        for (JTextComponent c : campos) {
            c.setText(vazio);
        }
    }
    
    /**
     * Limpar campos de JTextComponent
     * @param campos - campos para setar string vazia
    */
    public static void limparCampos(JLabel... campos) {
        String vazio = "";
        for (JLabel c : campos) {
            c.setText(vazio);
        }
    }    

    /**
     * Maximizar JInternalFrame para preencher o tamanho do frame pai.
     * @param frame - frame a ser maximizado.
    */   
    public static void maximizarJInternalFrame(JInternalFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setPreferredSize(new Dimension(screenSize.width, 
                screenSize.height));  
    }

    /**
     * Remove Barra de titulo e borda do frame
     * @param frame - tela a ser removido a barra e borda
     * @throws Exception 
    */      
    public static void removerBarraTituloEBorda(JInternalFrame frame) throws Exception{
        BasicInternalFrameUI bif = (BasicInternalFrameUI) frame.getUI();
        bif.setNorthPane(null);
        frame.setBorder(null);       
    }
    
    /**
     * Dsabilita ou Habilita componentes JButton passados por parâmetro.
     * @param valor - valor booleano true habilita, false desabilita.
     * @param botoes - botões para habilitar ou desabilitar
    */     
    public static void habilitarComponentes(boolean valor, JComponent ...componentes) {
        for (JComponent b : componentes) {
            b.setEnabled(valor);
        }
    }
    
}
