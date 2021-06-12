package be.com.alura.Teste;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import be.com.alura.Model.Produto;

//Stax Trabalha com eventos, pegando somente oq precisa
public class LeXmlStax {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("src/vendas.xml"); //Imputando o arquivo
		XMLInputFactory factory = XMLInputFactory.newInstance(); //Fábrica que consegue a instância de um xml
		XMLEventReader eventos = factory.createXMLEventReader(is); //Responsável por eventos
		List<Produto> produtos = new ArrayList<>(); //Coleção lista de produtos
		
		//Percorre os eventos
		while (eventos.hasNext()) {
			//pega proximo produto
			XMLEvent evento = eventos.nextEvent();
			//se for um produto cria o produto
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {
				Produto produto = criaUmProduto(eventos); //Invoca método passando o evento
				produtos.add(produto); //Add um produto
			}
		}
		System.out.println(produtos); //imprime os produtos encontrados no .xml
	}
	
	private static Produto criaUmProduto(XMLEventReader eventos) throws Exception {
		Produto produto = new Produto();
		
		//Percorre os eventos
		while (eventos.hasNext()) {
			//pega proximo evento
			XMLEvent evento = eventos.nextEvent();
			//se for nome captura e seta no produto
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();
				String nome = evento.asCharacters().getData();
				produto.setNome(nome);
			//se for preco captura e seta no produto	
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {
				evento = eventos.nextEvent();
				String conteudo = evento.asCharacters().getData();
				double preco = Double.parseDouble(conteudo);
				produto.setPreco(preco);
				//se for produto parar o laço	
			} else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {
				break;
			}
		}
		return produto;
	}
}
