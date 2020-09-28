package decoratorpagamentocartao.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UtilObjetos {

	/**
	 * Retorna true se a colecao for null ou vazia. 
	 * @param collection
	 * @return true | false
	 */
	public static Boolean ehNuloOuVazio(Collection<?> colecao) {
		if (colecao == null || colecao.isEmpty()) {
			return true;
		}			
		return false;
	}
	
	/**
	 * Retorna true se o mapa for null ou vazio. 
	 * @param Map
	 * @return true | false
	 */
	public static Boolean ehNuloOuVazio(Map<?, ?> mapa) {
		if (mapa == null || mapa.isEmpty()) {
			return true;
		}			
		return false;
	}

	/**
	 * Retorna true se o objeto for null. 
	 * @param Object
	 * @return true | false
	 */
	public static Boolean ehNuloOuVazio(Object objeto) {
		if (objeto == null) {
			return true;
		}			
		return false;
	}

	/**
	 * Retorna true se o array de objeto for null ou igual a zero.
	 * @param Object[]
	 * @return true | false
	 */
	public static Boolean ehNuloOuVazio(Object[] objeto) {
		if (objeto == null || objeto.length == 0) {
			return true;
		}			
		return false;
	}
	
	/**
	 * Retorna true se o string for null ou tiver tamanho igual a zero. 
	 * @param String
	 * @return true | false
	 */
	public static Boolean ehNuloOuVazio(String string) {
		if (string == null || string.trim().length() == 0) {
			return true;
		}			
		return false;
	}
	
	/**
	 * Percorre as propriedades String de um objeto e realiza o trim() em cada uma delas.
	 * Obs: Retorna a mesma refer�ncia de mem�ria
	 * @param object
	 * @return T
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T truncarCamposString(Object object) throws Exception {
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
			field.setAccessible(true);
			
			if (field.getType() == String.class) {
				
				String value = String.valueOf(field.get(object)).trim();
				
				if (value.equalsIgnoreCase("null")) {
					value = "";
				}
				
				field.set(object, value);
				 
			}
		}
		
		return (T) object;
		
	}
	
	/**
	 * Percorre as propriedades String de um objeto e realiza o trim() em cada uma delas.
	 * Obs: Retorna a mesma refer�ncia de mem�ria
	 * @param object
	 * @return T
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T truncarCamposString(Object object, boolean preencherCamposStringNulos) throws Exception {
		
		for (Field field : object.getClass().getDeclaredFields()) {
			
			field.setAccessible(true);
			
			if (field.getType() == String.class) {
				
				if (preencherCamposStringNulos && UtilObjetos.ehNuloOuVazio(field.get(object))) {
					
					field.set(object, "");
					
				} else {
					
					String value = String.valueOf(field.get(object)).trim();
					
					if (value.equalsIgnoreCase("null")) {
						value = "";
					}
					
					field.set(object, value);
					
				}
				 
			}
		}
		
		return (T) object;
		
	}	
	
	/**
	 * Copia o conteudo de um objeto para outro, desde que sejam Instancias da mesma classe
	 * @param input
	 * @param output
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clonarObjeto(Object input, Object output){
		try {
			if (input.getClass() == output.getClass()) {
				for (Field field : input.getClass().getDeclaredFields()) {
					if (!field.getName().equals("serialVersionUID")) {
						field.setAccessible(true);
						field.set(output, field.get(input));
					}
				}
				return (T) output;	
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Recebe um objeto, verifica se o mesmo extende uma classe e retorna o conte&uacute;do do objeto pai.
	 * @param input
	 * @return Object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T pegarObjetoPai(Object input){
		try {
			if (input.getClass().getSuperclass() != null && input.getClass().getSuperclass() != Object.class) {
				
				Object output = input.getClass().getSuperclass().newInstance();
				
				for (Field field : input.getClass().getSuperclass().getDeclaredFields()) {
					
					if (!field.getName().equals("serialVersionUID")) {
						
						field = input.getClass().getSuperclass().getDeclaredField(field.getName());
						
						field.setAccessible(true);
						
						field.set(output, field.get(input));
						
					}
					
				}
				
				return (T) output;
				
			} else {
				
				return null;
				
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T pegarObjetoFilho(Object input, Class<?> clazz) {
		try {
			
			Object output = null;
			
			for (Field field : input.getClass().getDeclaredFields()) {
				
				if (field.getType() == clazz) {					
					
					field.setAccessible(true);
					
					output = field.getType().newInstance();
					
					for (Field fieldChild : field.getType().getDeclaredFields()) {
						
						if (!fieldChild.getName().equals("serialVersionUID")) {
							
							fieldChild.setAccessible(true);
							
							fieldChild.set(output, fieldChild.get(field.get(input)));
							
						}
						
					}
					
				}
				
			}
			
			return (T) output;
			
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	/**
	 * Preenche os atributos de um objeto com os valores default de cria��o da instancia
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T preencherAtributos(Object object) {
		try {
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if (!field.getType().isPrimitive()) {
					if (field.getType().isEnum() || field.getType().equals(List.class)) {
						field.set(object, null);
					} else if (field.getType().equals(Integer.class)) {
						field.set(object, new Integer(0));
					} else if (field.getType().equals(BigDecimal.class)) {
						field.set(object, BigDecimal.ZERO);
					} else {
						field.set(object, field.getType().newInstance());						
					}
					if (field.getType().getPackage().equals(object.getClass().getPackage())) {
						preencherAtributos(field.get(object));
					}
				}
			}
			return (T) object;
		} catch (Exception e) {
			return null;
		}
	}
}
