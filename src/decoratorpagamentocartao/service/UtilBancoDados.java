/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpagamentocartao.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class UtilBancoDados {
    
    private static final String HOME_USUARIO            = System.getProperty("user.home");
    private static final String PASTA_PADRAO_SISTEMA    = "Sistema";
    private static final String PASTA_BANCO_DADOS       = "bancoDados";  
    private static final String PATH_BANCO_DADOS        = HOME_USUARIO + File.separator 
                                                                       + PASTA_PADRAO_SISTEMA + File.separator 
                                                                       + PASTA_BANCO_DADOS;

    public static String getPathArquivoBanco(Class classe) {
        File arquivo = null;
        
        String caminhoCompleto = PATH_BANCO_DADOS + File.separator + classe.getSimpleName() + ".json";
        
        Path arquivoBanco =  Paths.get(caminhoCompleto);
        
//      Verifica se o diretorio não existe, se não existir, cria os diretórios.
        if (!Files.exists(arquivoBanco)) {

            FileWriter fw = null;
            
            try {
                arquivo = new File(caminhoCompleto);
                
                fw = new FileWriter(arquivo);
                
            } catch (IOException ex) {
                
                Logger.getLogger(UtilBancoDados.class.getName()).log(Level.SEVERE, null, ex);
                
            } finally {
                
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(UtilBancoDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        
        return caminhoCompleto;
    }
    
    
}
