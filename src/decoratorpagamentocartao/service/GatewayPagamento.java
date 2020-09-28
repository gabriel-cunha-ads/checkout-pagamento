
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.Pedido;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public interface GatewayPagamento {

    boolean autorizar(Pedido pedido) throws GatewayPagamentoExeption;
    
}
