
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.Cliente;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class ClienteService {
    
    public boolean isClienteNegativo (Cliente cliente) {
        
        if (cliente.getCpf().contains("12345")) {
            return true;
        }
        
        return false;
    }
    
}
