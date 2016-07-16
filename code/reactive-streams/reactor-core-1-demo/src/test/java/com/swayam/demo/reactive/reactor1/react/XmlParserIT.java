package com.swayam.demo.reactive.reactor1.react;

import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swayam.demo.reactive.reactor1.model.LineItemRow;

import reactor.core.Environment;
import reactor.core.composable.Stream;

public class XmlParserIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParserIT.class);

    @Test
    public void testParse() throws IOException, InterruptedException {

	Environment environment = new Environment();
	// Dispatcher dispatcher = new ThreadPoolExecutorDispatcher(1, 128);
	// environment.addDispatcher(ThreadPoolExecutorDispatcher.class.getSimpleName(),
	// dispatcher);

	XmlParser xmlParser = new XmlParser(environment);
	Stream<LineItemRow> stream = xmlParser.parse(new GZIPInputStream(XmlParserIT.class.getResourceAsStream("/datasets/xml/www.cs.washington.edu/lineitem.xml.gz")));

	stream.consume((LineItemRow lineItemRow) -> {
	    LOGGER.info("new event {}", lineItemRow);
	});

	Thread.sleep(10_000);

    }

}
