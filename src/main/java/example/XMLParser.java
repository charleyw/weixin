package example;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class XMLParser {
    private Reader docReader;
    private Document document;

    public XMLParser(Reader docReader){
        this.docReader = docReader;
        SAXBuilder builder = new SAXBuilder();
        try {
            document = (Document) builder.build(docReader);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTextByTagName(String tagName) {
        Element rootNode = document.getRootElement();
        return rootNode.getChildText(tagName);
    }
}
