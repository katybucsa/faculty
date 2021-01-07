package factory;

import domain.Student;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XmlStudentRepo extends AbstractRepository<Integer, Student> {
    private String file;
    private DocumentBuilderFactory dbf;

    public XmlStudentRepo(Validator<Student> v, String f) {
        super(v);
        file = f;
        dbf = DocumentBuilderFactory.newInstance();
        readFromXml();
    }

    /*
       private void readFromXml() {
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = (Document) db.parse(new File(file));
                Element el = doc.getDocumentElement();
                NodeList nl = el.getElementsByTagName("student");
                for (int i = 0; i < nl.getLength(); i++) {
                    Element item = (Element) nl.item(i);
                    int id = Integer.parseInt(item.getAttribute("id"));
                    String nume = item.getAttribute("nume");
                    int grupa = Integer.parseInt(item.getAttribute("grupa"));
                    String email = item.getAttribute("email");
                    String indrumator = item.getAttribute("indrumator");
                    Student s = new Student(id, nume, grupa, email, indrumator);
                    super.save(s);
                }
            } catch (ParserConfigurationException e) {
                throw new RepoException("Eroare la parsare!\n");
            } catch (IOException e) {
                throw new RepoException("Citirea din fisier a esuat!\n");
            } catch (SAXException e) {
                throw new RepoException("Eroare la parsarea fisierului!\n");
            }
        }


        private void writeToXml() {
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.newDocument();
                Element el = doc.createElement("students");
                for (Student s : super.findAll()) {
                    Element se = doc.createElement("student");
                    se.setAttribute("id", s.getID().toString());
                    se.setAttribute("nume", s.getNume());
                    se.setAttribute("grupa", String.valueOf(s.getGrupa()));
                    se.setAttribute("email", s.getEmail());
                    se.setAttribute("indrumator", s.getIndrumator());
                    el.appendChild(se);
                }
                doc.appendChild(el);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource domSource = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(new File(file));
                t.transform(domSource, streamResult);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }

    */
    private void readFromXml() {
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(file));
            Element el = doc.getDocumentElement();
            NodeList nl = el.getElementsByTagName("student");
            for (int i = 0; i < nl.getLength(); i++) {
                Element item = (Element) nl.item(i);
                int id = Integer.parseInt(item.getAttribute("id"));
                NodeList nl1 = item.getElementsByTagName("nume");
                String nume = nl1.item(0).getChildNodes().item(0).getNodeValue();
                nl1 = item.getElementsByTagName("grupa");
                int grupa = Integer.parseInt(nl1.item(0).getChildNodes().item(0).getNodeValue());
                nl1 = item.getElementsByTagName("email");
                String email = nl1.item(0).getChildNodes().item(0).getNodeValue();
                nl1 = item.getElementsByTagName("indrumator");
                String indrumator = nl1.item(0).getChildNodes().item(0).getNodeValue();
                Student s = new Student(id, nume, grupa, email, indrumator);
                super.save(s);
            }
        } catch (ParserConfigurationException e) {
            throw new RepoException("Eroare la parsare!\n");
        } catch (IOException e) {
            throw new RepoException("Citirea din fisier a esuat!\n");
        } catch (SAXException e) {
            throw new RepoException("Eroare la parsarea fisierului!\n");
        }
    }

    private Element createElem(String s, Document d, Student st) {
        Element e = d.createElement(s);
        Text t = d.createTextNode(st.getNume());
        if (s.equals("grupa"))
            t = d.createTextNode(String.valueOf(st.getGrupa()));
        if (s.equals("email"))
            t = d.createTextNode(st.getEmail());
        if (s.equals("indrumator"))
            t = d.createTextNode(st.getIndrumator());
        e.appendChild(t);
        return e;

    }

    private void writeToXml() {
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element el = doc.createElement("students");
            for (Student s : super.findAll()) {
                Element se = doc.createElement("student");
                se.setAttribute("id", s.getID().toString());
                se.appendChild(createElem("nume",doc,s));
                se.appendChild(createElem("grupa",doc,s));
                se.appendChild(createElem("email",doc,s));
                se.appendChild(createElem("indrumator",doc,s));
                el.appendChild(se);
            }
            doc.appendChild(el);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(file));
            t.transform(domSource, streamResult);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public Optional<Student> findOne(Integer id) {
        readFromXml();
        return super.findOne(id);
    }

    public Student save(Student s) {
        readFromXml();
        Student st = super.save(s);
        writeToXml();
        return st;
    }

    public Optional<Student> update(Student s) {
        readFromXml();
        Optional<Student> st = super.update(s);
        writeToXml();
        return st;
    }

    public int size() {
        readFromXml();
        return super.size();
    }

    public Iterable<Student> findAll() {
        readFromXml();
        return super.findAll();
    }

    public Optional<Student> delete(Integer id) {
        readFromXml();
        Optional<Student> s = super.delete(id);
        writeToXml();
        return s;
    }
}
