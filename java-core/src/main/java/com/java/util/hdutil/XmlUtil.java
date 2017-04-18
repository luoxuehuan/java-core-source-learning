package com.java.util.hdutil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * 用于对XML进行分析和组装的帮助类
 * 
 * @author lexond
 * @version 1.0
 */
public class XmlUtil {

	/**
	 * 创建XML元素，Element，如果不存在父元素不存在则创建新的文档Document，如果存在则直接将元素加到父元素节点下；若元素存在属性，
	 * 则将属性配置到元素中
	 * 
	 * @param parentElement
	 *            父元素
	 * @param elementName
	 *            元素名
	 * @param attributeMap
	 *            新增元素的属性组合
	 * @return 元素对象表示
	 */
	@SuppressWarnings("rawtypes")
	public static Element createElement(Element parentElement,
			String elementName, Map attributeMap) {

		Element newElement = null;

		if (parentElement == null) {
			Document document = DocumentHelper.createDocument();
			newElement = document.addElement(elementName);

		} else {
			newElement = parentElement.addElement(elementName);
		}

		// 如果存在属性集合
		if (attributeMap != null) {
			for (Iterator it = attributeMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				newElement.addAttribute(key, value);
			}
		}
		return newElement;

	}

	/**
	 * 将传过来的参数转化成XML格式的数据，最终返回XML形式的字符串
	 * 
	 * @param pathAndValue
	 *            封装了对应xml路径和值的Map
	 * @return 返回封装好的xml格式的数据，XML形式的字符串
	 */
	@SuppressWarnings("rawtypes")
	public static String assembleXml(Map pathAndValue) {
		Document document = DocumentHelper.createDocument();

		for (Iterator it = pathAndValue.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String path = entry.getKey().toString();
			String value = entry.getValue().toString();
			assembleXml(document, path, value);
		}

		return document.asXML();
	}

	/**
	 * 利用节点路径和节点值组装XML
	 * 
	 * @param document
	 *            组装的xmlSchema
	 * @param path
	 *            节点的路径
	 * @param value
	 *            节点值
	 */
	public static void assembleXml(Document document, String path, String value) {
		String[] paths = path.split("/");
		Element e = null;
		try {
			Node root = document.selectSingleNode(paths[0]);
			e = (Element) root;
		} catch (Exception e1) {
			e = document.addElement(paths[0]);
		}

		for (int i = 1; i < paths.length; i++) {
			String eleName = paths[i];
			Node tempNode = e.selectSingleNode(eleName);
			if (tempNode != null)
				e = (Element) tempNode;
			else
				e = e.addElement(eleName);
		}
		if (value == null)
			value = "";
		e.addText(value);
	}

	/**
	 * 利用schema来进行xml验证,有错误信息则返回错误信息
	 * 
	 * @param schemaXml
	 *            schema形式
	 * @param dataXml
	 *            xml数据
	 * @return 错误集合
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List checkXmlBySchema(final String schemaXml, String dataXml) {
		SAXReader reader = new SAXReader();

		reader.setValidation(true);

		try {

			EntityResolver resolver = new EntityResolver() {
				public InputSource resolveEntity(String publicId,
						String systemId) {
					StringReader in = new StringReader(schemaXml);
					return new InputSource(in);
				}
			};

			reader.setEntityResolver(resolver);

			String schemaLocation = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
			String validation = "http://xml.org/sax/features/validation";
			String validationSchema = "http://apache.org/xml/features/validation/schema";

			reader.setProperty(schemaLocation, "");
			reader.setFeature(validation, true);
			reader.setFeature(validationSchema, true);

			XMLErrorHandler errorHandler = new XMLErrorHandler();
			reader.setErrorHandler(errorHandler);

			// InputStream in = new ByteArrayInputStream(dataXml.getBytes());
			InputStream in = new ByteArrayInputStream(new String(
					dataXml.getBytes("iso-8859-1"), "utf-8").getBytes());
			// Document document = reader.read(in);
			reader.read(in);

			List errors = new ArrayList();
			Element e = errorHandler.getErrors();
			List l = e.elements();
			for (int i = 0; i < l.size(); i++) {
				Element ele = (Element) l.get(i);
				String errorInfo = ele.getText();
				System.out.println(errorInfo);

				// 利用正则表达式提取需要的字符
				String regular = "元素“(\\S+)”的";
				Pattern p = Pattern.compile(regular);
				Matcher m = p.matcher(errorInfo);
				if (m.find()) {
					String name = m.group(1);
					errors.add(name);
				}
			}
			return errors;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("在进行schema验证时发生错误!");
		}

	}

	/**
	 * 得到xml中对应path路径下的值
	 * 
	 * @param document
	 *            xml Document
	 * @param path
	 *            xml路径
	 * @return 对应的值
	 */
	public static String getNodeValue(Document document, String path) {
		Node node = document.selectSingleNode(path);

		if (node == null)
			return null;

		return node.getText();
	}

	/**
	 * 将字符串解析成xml文档格式
	 * 
	 * @param dataXml
	 *            包含数据的xml
	 * @return xml的Document的表示
	 */
	public static Document parseStringToXml(String dataXml) {

		// 如果不是规范的xml语句则返回空指针
		if (dataXml == null || dataXml.trim().equals(""))
			return null;

		Document dataDocument = null;
		try {
			dataDocument = DocumentHelper.parseText(dataXml);
		} catch (DocumentException e) {
			System.out.println("不能将  " + dataXml + "  转化成xml格式");
			System.out.println(e.getMessage());
			throw new RuntimeException("不能将  " + dataXml + "  转化成xml格式");
		}
		return dataDocument;
	}

	/**
	 * 根据Schema生成一个空的xml结构
	 * 
	 * @param schema
	 *            schema结构
	 * @return schema对应的文档
	 */
	@SuppressWarnings("rawtypes")
	public static Document constructXmlFromSchema(String schema) {

		final String ROOT_ELEMENT_PATH = "xs:element";
		final String COLUMN_ELEMENT_PATH = "xs:element/xs:complexType/xs:all";

		Document xml = DocumentHelper.createDocument();

		Document schemaDocument = parseStringToXml(schema);
		Element rootElement = schemaDocument.getRootElement();

		Element rootEle = (Element) rootElement
				.selectSingleNode(ROOT_ELEMENT_PATH);

		String rootEleName = rootEle.attributeValue("name");
		Element xmlRootElement = xml.addElement(rootEleName);

		Element columnsEle = (Element) rootElement
				.selectSingleNode(COLUMN_ELEMENT_PATH);

		for (Iterator it = columnsEle.elementIterator(); it.hasNext();) {
			Element columnEle = (Element) it.next();
			String columnEleName = columnEle.attributeValue("name");
			xmlRootElement.addElement(columnEleName);
		}

		System.out.println(xml.asXML());

		return xml;

	}

}
