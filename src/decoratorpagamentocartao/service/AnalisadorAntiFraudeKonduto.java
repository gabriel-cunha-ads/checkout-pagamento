/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.EnumAutorizacao;
import decoratorpagamentocartao.entity.Pedido;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class AnalisadorAntiFraudeKonduto implements GatewayPagamento, AnalisadorAntiFraude{

    private GatewayPagamento gatewayPagamento;
    
    private ClienteService clienteService;    

    public AnalisadorAntiFraudeKonduto(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
        clienteService = new ClienteService();
    }
    
    @Override
    public boolean autorizar(Pedido pedido) throws GatewayPagamentoExeption{

        boolean isClienteNegativo = clienteService.isClienteNegativo(pedido.getCliente());
        
        EnumAutorizacao autorizacaoFraude = this.analisar(pedido);
        
        boolean isAutorizadoGateway = gatewayPagamento.autorizar(pedido);
        
        boolean isVendaAprovada = !isClienteNegativo 
                                  && autorizacaoFraude.equals(EnumAutorizacao.AUTORIZADO)
                                  && isAutorizadoGateway;
        
        if (isVendaAprovada) {
            return true;
        }
        return false;
    }

    @Override
    public EnumAutorizacao analisar(Pedido pedido) {
        
        String nomeCartaoBaseDadosAntiFraude = "Martin Fowler";
                
        if (!pedido.getCliente().getNome().equals(nomeCartaoBaseDadosAntiFraude)) {
            throw new RiscoTransacaoCartaoException("Nome cartão inválido");
        } else {
            return EnumAutorizacao.AUTORIZADO;
        }
    }
    
}
