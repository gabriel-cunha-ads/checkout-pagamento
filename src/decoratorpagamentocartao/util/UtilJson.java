package decoratorpagamentocartao.util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.Reader;

public class UtilJson {
	
	private String formatoData = "yyyy-MM-dd HH:mm:ss";
	
	private Boolean incluirNulos = true;
	
	private Boolean usarJaxb = true;
	
	private Boolean formatar = true;
	
	private Boolean desabilitarHtmlEscaping = false;
	
	private ExclusionStrategy exclusionStrategy = new Exclusions();
	
	private FieldNamingStrategy fieldNamingStrategy = new CustomFieldNamePolicy();
	
	/**
	 * Permite utilizar um formato de data espec&iacute;fico.<br>
	 * O formato padr&atilde;o &eacute;: yyyy-MM-dd HH:mm:ss<br>
	 * <br>
	 * Exemplo de uso:<br>
	 * String json = new UtilJson().comFormatoData("yyyy-MM-dd").toJson(obj);
	 * @param pattern
	 * @return
	 */	
	public UtilJson comFormatoData(String pattern) {
		this.formatoData = pattern;
		return this;
	}
	
	public UtilJson semJaxb(){
		this.usarJaxb = false;
		return this;
	}
	
	public UtilJson removerNulos(){
		this.incluirNulos = false;
		return this;
	}
	
	public UtilJson semFormatar(){
		this.formatar = false;
		return this;
	}
	
	public UtilJson desabilitarHtmlEscaping() {
		this.desabilitarHtmlEscaping = true;
		return this;
	}
	
	/**
	 * Converte um json que contenha cabecalho em Objeto T passado como parametro<br>
	 * Exemplo do JSON:<br>
	 * {@code {"output": {"success": "OK"}"}}<br>
	 * @param jsonString json deve conter cabecalho, exemplo 
	 * @param cabecalho utilize a classe {@link Retorno}
	 * @param classe a classe de retorno desejada
	 * @return T Tipo do objeto 
	 */
	public <T> T jsonToObject(String jsonString, String cabecalho, Class<T> classe){
		
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);
		jsonString = jsonObject.get(cabecalho).toString();
		
		return transformar().fromJson(jsonString, classe);
		
	}
	
	/**
	 * Converte um json em Objeto T passado com parametro<br>
	 * Exemplo do JSON:<br>
	 * {@code {"success": "OK"}"}}<br>
	 * @param jsonString 
	 * @param classe a classe de retorno desejada
	 * @return T Tipo do objeto 
	 */	
	public <T> T jsonToObject(String jsonString, Class<T> classe){
            return transformar().fromJson(jsonString, classe);
	}
	
	/**
	 * Converte um json em Objeto T passado com parametro, matendo a mesma instancia de entrada<br>
	 * Exemplo do JSON:<br>
	 * {@code {"success": "OK"}"}}<br>
	 * @param jsonString 
	 * @param classe a classe de retorno desejada
	 * @return T Tipo do objeto 
	 */	
	@SuppressWarnings("unchecked")
	public <T> T jsonToObject(String jsonString, Object output) {
		
		Object object = transformar().fromJson(jsonString, output.getClass());
		
		return (T) UtilObjetos.clonarObjeto(object, output);
		
	}
	
	/**
	 * Converte um array json em List, passando como parametro, o Tipo da lista a ser retornada<br>
	 * <br>
	 * Exemplo do JSON:<br>
	 * {@code {[{"code":"0100871300015857509","status":"Internal Error"}]}<br>
	 * <br>
	 * @param jsonString json
	 * @param classe Tipo da Lista
	 * @return
	 */	
	public <T> List<T> jsonToList(String jsonString, Class<T> classe){
		
		Type type = TypeToken.getParameterized(ArrayList.class, classe).getType();
		
		return transformar().fromJson(jsonString, type);
		
	}
        
	public <T> List<T> jsonToList(Reader reader, Class<T> classe){
		
		Type type = TypeToken.getParameterized(ArrayList.class, classe).getType();
		
		return transformar().fromJson(reader, type);
		
	}        
	
	public <T> List<T> jsonToList(String jsonString, String cabecalho, Class<T> classe){
	
		JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);
		jsonString = jsonObject.get(cabecalho).toString();
		
		return jsonToList(jsonString, classe);
		
	}
	
	/**
	 * Converte qualquer objeto em JSON
	 * @param object
	 * @return json
	 */	
	public String toJson(Object objeto) {
		
		return transformar().toJson(objeto);
		
	}
	
	/**
	 * Converte uma String para JSON
	 * @param chave
	 * @param valor
	 * @return {"chave": "valor"}
	 */
	public String toJson(String chave, String valor){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(chave, valor);
		return jsonObject.toString();
	}
	
	/**
	 * Converte um objeto para JSON adicionando a chave (nome do objeto pai)
	 * @param chave
	 * @param valor
	 * @return {"chave": "valor"}
	 */
	public String toJson(String chave, Object object){
		
		JsonElement jsonElement = transformar().toJsonTree(object);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.add(chave, jsonElement);
		
		return transformar().toJson(jsonObject);
		
	}
	
	/**
	 * Retorna o valor de um json como String<br>
	 * Exemplo:{@code {"token":"198c460e690af02580112013978a8839"}}<br>
	 * @param jsonString json deve conter chave + valor
	 * @param chave utilize a classe {@link Retorno}
	 * @return valor
	 */
	public String getValor(String jsonString, String chave){
		
		JsonObject jsonObject = transformar().fromJson(jsonString, JsonObject.class);
		
		return jsonObject.get(chave).getAsString();
		
	}
		
	/**
	 * Recebe uma String e transforma em json.<br>
	 * @param String 
	 * @return String JSON formatado
	 */	
	public String formatar(String json){
		
		return transformar().toJson(JsonParser.parseString(json));
	}
        
	/**
	 * M&eacute;todo que realiza a configura&ccedil;&atilde;o padr&atilde;o de convers&atilde;o
	 * @return Gson
	 */
	public Gson transformar(){
		
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(formatoData);
		builder.registerTypeAdapter(Date.class, obterJsonDeserializerDate());
		builder.registerTypeAdapterFactory(new AdapterEnum());
		
		if (usarJaxb) {
			
			builder.setFieldNamingStrategy(fieldNamingStrategy);
			builder.addDeserializationExclusionStrategy(exclusionStrategy);
			builder.addSerializationExclusionStrategy(exclusionStrategy);
			
		}
		
		if (incluirNulos) {
			builder.serializeNulls();
		}
		
		if (formatar) {
			builder.setPrettyPrinting();
		}
		
		if (desabilitarHtmlEscaping) {
			builder.disableHtmlEscaping();
		}
		
		return builder.create();
		
	}
	
	/**-------------------------METODOS PRIVADOS------------------------------*/
	/**
	 */
	private Object obterJsonDeserializerDate() {
		
		return new JsonDeserializer<Date>() {

			DateFormat df = new SimpleDateFormat(formatoData);
			
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				try {
					
					return df.parse(json.getAsString());
					
				} catch (ParseException e) {
					return null;
				}
			}			
		};		
	}	
	
	private class CustomFieldNamePolicy implements FieldNamingStrategy {
		
		@Override
		public String translateName(Field paramField) {
			Annotation annotationName = null;
			if (null != (annotationName = paramField.getAnnotation(XmlElementWrapper.class))) {
				Annotation element = null;
				if (Collection.class.isAssignableFrom(paramField.getType()) || Map.class.isAssignableFrom(paramField.getType())) {
					return ((XmlElementWrapper) annotationName).name();	
				} else if (null != (element = paramField.getAnnotation(XmlElement.class))) {
					return ((XmlElement) element).name();
				} else {
					return ((XmlElementWrapper) annotationName).name();	
				}
			} else if (null != (annotationName = paramField.getAnnotation(XmlElement.class))){
				return ((XmlElement) annotationName).name();
			} else if((null != (annotationName = paramField.getAnnotation(XmlAttribute.class)))) {
				return ((XmlAttribute) annotationName).name();
			} else if((null != (annotationName = paramField.getAnnotation(XmlEnumValue.class)))) {
				return ((XmlEnumValue) annotationName).value();
			} else if((null != (annotationName = paramField.getAnnotation(XmlRootElement.class)))) {
				return ((XmlRootElement) annotationName).name();
			}
			return paramField.getName();
		}
		
	}
	
	private class Exclusions implements ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes field) {
			if (null != field.getAnnotation(XmlTransient.class)) {
				return true;
			}
			return false;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {			
			return false;
		}
		
	}
	
	private class AdapterEnum implements TypeAdapterFactory {

		@SuppressWarnings("unchecked")
		@Override
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

			Class<T> rawType = (Class<T>) type.getRawType();
			
			if (!rawType.isEnum()) {
				return null;
			}
			
			final Map<String, T> mapConstant = new  HashMap<String, T>();
			
			for (T constant : rawType.getEnumConstants()) {
				mapConstant.put(constant.toString(), constant);
			}
			
			return new TypeAdapter<T>() {

				@Override
				public void write(JsonWriter out, T value) throws IOException {
					if (value == null) {
						out.nullValue();
					} else {
						
						if (mapConstant.containsValue(value)) {
							
							for (Entry<String, T> enumValue : mapConstant.entrySet()) {
								
								if (enumValue.getValue().equals(value)) {
									
									out.value(enumValue.getValue().toString());
									
								} else if (enumValue.getValue().getClass().equals(String.class) && 
										value.getClass().equals(String.class)) {
									
									if (enumValue.getValue().toString().equalsIgnoreCase(value.toString())) {
										out.value(enumValue.getValue().toString());	
									}
									
								}
								
							}
							
						}
						
					}
				}

				@Override
				public T read(JsonReader in) throws IOException {
					if (in.peek() == JsonToken.NULL) {
						in.nextNull();
						return null;
					} else {
						String inputText = in.nextString();
						if (!UtilObjetos.ehNuloOuVazio(mapConstant.get(inputText))) {
							return mapConstant.get(inputText);
						} else {
							return mapConstant.get(inputText.toLowerCase());
						}
					}
				}
				
			};

		}
		
	}
	
}
