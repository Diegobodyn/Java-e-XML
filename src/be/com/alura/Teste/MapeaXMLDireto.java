package be.com.alura.Teste;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import be.com.alura.Model.Produto;
import be.com.alura.Model.Venda;

//Usando JAXB
public class MapeaXMLDireto {
	public static void main(String[] args) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);
		// Do xml para o Objeto
		xmlParaObjeto(jaxbContext);
		// Do Objeto para o XML
		objetoParaXml(jaxbContext);
	}
	
	private static void xmlParaObjeto(JAXBContext jaxbContext) throws Exception {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Venda venda = (Venda) unmarshaller.unmarshal(new File("src/vendas.xml"));
		System.out.println(venda);

	}

	private static void objetoParaXml(JAXBContext jaxbContext) throws JAXBException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		Venda venda = new Venda();
		venda.setFormaDePagamento("Crediario");
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto("Livro de Java", 59.90));
		produtos.add(new Produto("Livro de xml", 59.90));
		produtos.add(new Produto("Livro de O.O", 59.90));
		venda.setProdutos(produtos);
		StringWriter writer = new StringWriter();
		marshaller.marshal(venda, writer);

		System.out.println(writer.toString());
	}
}
