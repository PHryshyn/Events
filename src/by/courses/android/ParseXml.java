package by.courses.android;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseXml implements Parse {

    public ParseXml() {
    }

    private static File file;
    private DocumentBuilder db;

    @Override
    public void parseFile(String fileName) {
        try{
           if (!file.exists()) {
                System.out.println("File isn't open");
            } else {
                Document document = db.parse(file);
                Element docElement = document.getDocumentElement();

                //adding root element in console
                System.out.println("Root element: "
                        + docElement.getNodeName());
                NodeList eventList = docElement.getElementsByTagName("event");

                //Print information about event
                if (eventList != null && eventList.getLength()>0){
                    for (int i=0; i<eventList.getLength(); i++){
                        Node node = eventList.item(i);

                        if (node.getNodeType() == Node.ELEMENT_NODE){
                            System.out.println("==========================================");
                            Element eElement = (Element) node;

                            // print date and time
                            NodeList nodeList = eElement.getElementsByTagName("datetime");
                            System.out.println("Date: "
                                    + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            // print url
                            nodeList = eElement.getElementsByTagName("url");
                            System.out.println("URL: "
                                    + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            // print name
                            nodeList = eElement.getElementsByTagName("name");
                            System.out.println("Name: "
                                    + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            // print city
                            nodeList = eElement.getElementsByTagName("city");
                            System.out.println("City: "
                                    + nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            // print country
                            nodeList = eElement.getElementsByTagName("country");
                            System.out.println("Country: "
                                    + nodeList.item(0).getChildNodes().item(0).getNodeValue());
                        }
                    }
                } else {
                    System.out.println("The list of events is empty");
                }
           }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void openFile(String fileName) {
        try{
            file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
            System.out.println("File open");
        }
        catch (Exception e){
            System.out.println(e);
        }


    }

    public static void main(String args[]){
      ParseXml parser = new ParseXml();
        parser.openFile("resources//eventsMaroon.xml");
        parser.parseFile("resources//eventsMaroon.xml");


    }
}
