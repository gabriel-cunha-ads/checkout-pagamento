/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.Pedido;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public  abstract class GatewayPagamentoDecorator implements GatewayPagamento{
    
    private GatewayPagamento gatewayPagamento;
    
    private ClienteService clienteService;

    public GatewayPagamentoDecorator(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
        clienteService = new ClienteService();
    }    

    @Override
    public boolean autorizar(Pedido pedido) throws GatewayPagamentoExeption {
        return gatewayPagamento.autorizar(pedido);
    }
    
}
