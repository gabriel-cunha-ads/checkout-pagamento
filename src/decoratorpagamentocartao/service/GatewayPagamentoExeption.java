/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpagamentocartao.service;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class GatewayPagamentoExeption extends Exception {

    public GatewayPagamentoExeption() {
        super();
    }
    
    public GatewayPagamentoExeption(String mensagem) {
        super(mensagem);
    }
}
