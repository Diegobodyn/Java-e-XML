package be.com.alura.Teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import be.com.alura.Handlers.ProdutosHendler;

//Sax Necess�rio um Hendler que faz todo a l�gica.
public class LeXmlComSax {
	public static void main(String[] args) throws Exception {
		
		//Usa o XMLReader
		XMLReader leitor = XMLReaderFactory.createXMLReader();
		//Necessita de um ProdutosHendler
		ProdutosHendler logica = new ProdutosHendler();
		//Atribui o valor encontrado no ProdutosHendler
		leitor.setContentHandler(logica);
		//Acessa o arquivo .xml
		InputStream ips = new FileInputStream("src/vendas.xml");
		//L� o arquivo .xml
		InputSource is = new InputSource(ips);
		leitor.parse(is);
		
		System.out.println(logica.getProdutos());
	}
}
