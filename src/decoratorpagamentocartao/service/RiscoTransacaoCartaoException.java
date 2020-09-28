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
public class RiscoTransacaoCartaoException extends RuntimeException {

    public RiscoTransacaoCartaoException() {
        super();
    }
    
    public RiscoTransacaoCartaoException(String mensagem) {
        super(mensagem);
    }
}
