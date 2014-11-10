package de.unikoblenz.west.rdf.generator.test;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.model.Graph;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.turtle.TurtleWriterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unikoblenz.west.rdf.generator.RdfGenerator;
import de.unikoblenz.west.rdf.generator.RdfGeneratorException;
import de.unikoblenz.west.rdf.generator.impl.RandomRdfGenerator;

/**
 * tests RdfGenerator implementations
 * @author lkastler
 */
public class TestRdfGenerator {

	private final Logger log = LoggerFactory.getLogger(TestRdfGenerator.class);
	
	@Before
	public void setUp() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void testSimpleRdfGeneration() {
		log.info("testing generator");
		
		RdfGenerator gen = new RandomRdfGenerator(null);
		
		try {
			Graph g = gen.generate();
			
			RDFWriter write = new TurtleWriterFactory().getWriter(new FileWriter("test.ttl")); 
			
			write.startRDF();
			
			for(Statement s : g) {
				write.handleStatement(s);
			}
			
			write.endRDF();
			
			log.info("done");
		} catch (RdfGeneratorException e) {
			log.error("could not generate graph", e);
		} catch (IOException e) {
			log.error("could not write file", e);
		} catch (RDFHandlerException e) {
			log.error("could not write RDF", e);
		}
		
		
	}
}
