/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpagamentocartao.service;

import decoratorpagamentocartao.entity.CartaoCredito;
import decoratorpagamentocartao.entity.EnumAutorizacao;
import decoratorpagamentocartao.entity.Pedido;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public interface AnalisadorAntiFraude {
    
    public EnumAutorizacao analisar(Pedido pedido);
    
}
