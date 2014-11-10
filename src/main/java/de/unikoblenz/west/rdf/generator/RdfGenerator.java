package de.unikoblenz.west.rdf.generator;

import org.openrdf.model.Graph;

/**
 * provides methods to generate RDF data.
 * 
 * @author lkastler
 */
public interface RdfGenerator {

	/**
	 * generates an RDF graph according to implemented model.
	 * returns generated RDF graph.
	 * @throws RdfGeneratorException - thrown if something went wrong.
	 */
	public Graph generate() throws RdfGeneratorException;
}
