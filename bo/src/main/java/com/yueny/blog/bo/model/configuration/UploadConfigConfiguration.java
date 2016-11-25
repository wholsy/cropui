/**
 *
 */
package com.yueny.blog.bo.model.configuration;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author yueny09 <deep_blue_yang@163.com>
 *
 * @DATE 2016年9月22日 下午5:22:52
 *
 */
public final class UploadConfigConfiguration {
	private static JAXBElement<UploadConfigModel> uploadConfig = null;
	static {
		try {
			final JAXBContext cxt = JAXBContext.newInstance(UploadConfigModel.class);
			final Unmarshaller unmarshaller = cxt.createUnmarshaller();
			final XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
			final InputStream is = UploadConfigConfiguration.class.getResourceAsStream("/config/xml/upload.xml");

			XMLStreamReader reader;
			try {
				reader = xmlFactory.createXMLStreamReader(is);
				uploadConfig = unmarshaller.unmarshal(reader, UploadConfigModel.class);
			} catch (final XMLStreamException e) {
				e.printStackTrace();
			}

		} catch (final JAXBException e) {
			e.printStackTrace();
		}
	}

	public static UploadConfigModel getUploadConfig() {
		return uploadConfig.getValue();
	}

}
