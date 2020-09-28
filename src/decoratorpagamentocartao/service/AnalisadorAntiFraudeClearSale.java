package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.EnumAutorizacao;
import decoratorpagamentocartao.entity.Pedido;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class AnalisadorAntiFraudeClearSale extends GatewayPagamentoDecorator implements AnalisadorAntiFraude{
    
    public AnalisadorAntiFraudeClearSale(GatewayPagamento gatewayPagamento) {
        super(gatewayPagamento);
    }

    @Override
    public boolean autorizar(Pedido pedido) throws GatewayPagamentoExeption{

        EnumAutorizacao autorizacaoFraude = this.analisar(pedido);
        
        boolean isAutorizadoGateway = super.autorizar(pedido);
        
        boolean isVendaAprovada = autorizacaoFraude.equals(EnumAutorizacao.AUTORIZADO)
                                  && isAutorizadoGateway;
        
        if (isVendaAprovada) {
            return true;
        }
        return false;
    }

    @Override
    public EnumAutorizacao analisar(Pedido pedido) {
        
        String nomeCartaoBaseDadosAntiFraude = "Robert C Martin";
                
        if (!pedido.getCliente().getNome().equals(nomeCartaoBaseDadosAntiFraude)) {
            
            return EnumAutorizacao.NAO_AUTORIZADO;
        } else {
            return EnumAutorizacao.AUTORIZADO;
        }
    }

}
