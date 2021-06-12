package be.com.alura.Teste;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ConversorParaHtml {
	public static void main(String[] args) throws Exception {
		//Imputando o .xsl
		InputStream xsl = new FileInputStream("src/xmlParaHtml.xsl");
		StreamSource xslSource = new StreamSource(xsl);
		//Imputando dados de um .xml
		InputStream dados = new FileInputStream("src/vendas.xml");
		StreamSource xmlSource = new StreamSource(dados);
		//Transformando
		Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);
		StreamResult saida = new StreamResult("src/vendas.html");
		transformer.transform(xmlSource, saida);
		
		System.out.println("Conversão concluída");
	}
}
