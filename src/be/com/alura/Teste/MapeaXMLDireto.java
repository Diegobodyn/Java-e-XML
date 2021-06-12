package be.com.alura.Teste;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import be.com.alura.Model.Venda;

public class MapeaXMLDireto {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
			Venda venda = (Venda) unmarshaller.unmarshal(new File("src/vendas.xml"));
			
			System.out.println(venda);
	}

}
