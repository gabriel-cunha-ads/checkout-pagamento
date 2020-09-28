
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.Pedido;
import decoratorpagamentocartao.util.UtilJson;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class GatewayCielo implements GatewayPagamento{

    @Override
    public boolean autorizar(Pedido pedido) throws GatewayPagamentoExeption{
        
//      Simula o envio para API da cielo autorizar a transação no cartão do cliente.
        
        if (pedido.getValorTotal() == 0) {
            throw new GatewayPagamentoExeption("Cod. 108 - Valor da transação deve ser maior que \"0\"");
        }         
        
        if (pedido.getCartaoCredito().getVencimento().isBefore(YearMonth.now())) {
            throw new GatewayPagamentoExeption("Cod. 126 - Validade do cartão inválida.");
        }        
        
        int codSeguranca = pedido.getCartaoCredito().getCodigoSeguranca();
        
        if ( codSeguranca == 999 || codSeguranca == 000){
            throw new GatewayPagamentoExeption("CVV (Código de segurança) inválido!");
        }
        
        try {
            FileWriter fr;
        
            String pathBancoGateway = UtilBancoDados.getPathArquivoBanco(Pedido.class);

            String jsonPedido = new UtilJson().toJson(pedido);
        
            fr = new FileWriter(pathBancoGateway);
            fr.write(jsonPedido);
            fr.close();
            
        } catch (IOException ex) {
            Logger.getLogger(GatewayCielo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
