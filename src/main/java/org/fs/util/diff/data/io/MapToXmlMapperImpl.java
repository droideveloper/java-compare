package org.fs.util.diff.data.io;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapToXmlMapperImpl implements MapToXmlMapper<String> {

    @Override
    public void setMap(Map<String, List<String>> source, File out) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element resources = document.createElement("resources");
            document.appendChild(resources);

            Attr attr = document.createAttribute("xmlns:tools");
            attr.setValue("http://schemas.android.com/tools");

            resources.setAttributeNode(attr);

            attr = document.createAttribute("tools:shrinkMode");
            attr.setValue("safe");

            resources.setAttributeNode(attr);

            final StringBuilder buffer = new StringBuilder();
            for (Map.Entry<String, List<String>> entry: source.entrySet()) {
                final String resourceType = entry.getKey();
                final List<String> input = entry.getValue();
                final String flatten = input.stream()
                        .map(s -> "@" + resourceType + "/" + s)
                        .collect(Collectors.joining(","));
                if (buffer.length() != 0) {
                    buffer.append(",");
                }
                buffer.append(flatten);
            }

            attr = document.createAttribute("tools:keep");
            attr.setValue(buffer.toString());
            resources.setAttributeNode(attr);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource dom = new DOMSource(document);
            StreamResult stream = new StreamResult(out);

            transformer.transform(dom, stream);

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
