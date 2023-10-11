package com.example.xml;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyXMLReader {

    @Test
    public void test1() throws Exception {
        System.out.println("===== test1 =====");
        long lasting = System.currentTimeMillis();
        try {
            InputStream f = MyXMLReader.class.getResourceAsStream("/xml/RESULT.xml");
            // File f = new File("xml/RESULT.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nodeList = doc.getElementsByTagName("VALUE");
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.print("车牌号码:" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
                System.out.println("车主地址:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        System.out.println("===== test1 =====");
        long lasting = System.currentTimeMillis();
        try {
            InputStream f = MyXMLReader.class.getResourceAsStream("/xml/student.xml");
            // File f = new File("/xml/student.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nodeList = doc.getElementsByTagName("student");
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println("firstname: " + doc.getElementsByTagName("firstname").item(i).getFirstChild().getNodeValue());
                System.out.println("lastname: " + doc.getElementsByTagName("lastname").item(i).getFirstChild().getNodeValue());
                System.out.println("nickname: " + doc.getElementsByTagName("nickname").item(i).getFirstChild().getNodeValue());
                System.out.println("marks: " + doc.getElementsByTagName("marks").item(i).getFirstChild().getNodeValue());
                // 节点不存在时，抛出 npe
                System.out.println("chinese: " + doc.getElementsByTagName("chinese").item(i).getFirstChild().getNodeValue());
                System.out.println("math: " + doc.getElementsByTagName("math").item(i).getFirstChild().getNodeValue());
                System.out.println("============================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws Exception {
        System.out.println("===== test1 =====");
        long lasting = System.currentTimeMillis();
        try {
            InputStream f = MyXMLReader.class.getResourceAsStream("/xml/student.xml");
            // File f = new File("/xml/student.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = doc.getElementsByTagName("student");
            // 或
            // NodeList nodeList = (NodeList) xPath.compile("/class/student")
            //         .evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                String firstname = xPath.compile("./firstname").evaluate(item);
                String lastname = xPath.compile("./lastname").evaluate(item);
                String nickname = xPath.compile("./nickname").evaluate(item);
                String marks = xPath.compile("./marks").evaluate(item);
                // 节点不存在时，也不会抛出异常
                String chinese = xPath.compile("./course/chinese").evaluate(item);
                String math = xPath.compile("./course/math").evaluate(item);
                System.out.println("firstname: " + firstname);
                System.out.println("lastname: " + lastname);
                System.out.println("nickname: " + nickname);
                System.out.println("marks: " + marks);
                System.out.println("chinese: " + chinese);
                System.out.println("math: " + math);
                System.out.println("============================================");
            }
            System.out.println("finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    // public void test3() throws Exception {
    //     System.out.println("===== test3 =====");
    //     InputStream f = MyXMLReader.class.getResourceAsStream("/xml/Tutorials.xml");
    //     File f = new File("/xml/Tutorials.xml");
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(f);
    //     XPath xPath = XPathFactory.newInstance().newXPath();
    //     String expression = "/Tutorials/Tutorial";
    //     nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
    // }

    @Test
    public void test5() throws Exception {
        System.out.println("===== test5 =====");
        String filePath = MyXMLReader.class.getResource("/xml/a.ls").getPath();
        final Pattern pattern = Pattern.compile("^(.*)(a\\.b\\..*\\.XML)$");
        List<String> readLines = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
        List<String> filenameList = readLines.stream()
                .map(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        return matcher.group(matcher.groupCount());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(filenameList);
    }
}