package de.unikoblenz.west.rdf.generator.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openrdf.model.Graph;
import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.TreeModel;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unikoblenz.west.rdf.generator.EdgeGenerationException;
import de.unikoblenz.west.rdf.generator.RdfGenerator;
import de.unikoblenz.west.rdf.generator.RdfGeneratorException;
import de.unikoblenz.west.rdf.generator.model.Edge;
import de.unikoblenz.west.rdf.generator.model.Vertex;

/**
 * implements a randomized graph as RDF graph.
 * @author lkastler
 */
public class RandomRdfGenerator implements RdfGenerator {

	private final Logger log = LoggerFactory.getLogger(RandomRdfGenerator.class); 
	
	private final int numberOfVertices;
	private final String namespace;
	private final int outDegreeThreshold;
	
	private final Random rand;
	
	// TODO use props
	public RandomRdfGenerator(Properties props) {
		namespace = "http://example.com/";
		numberOfVertices = 1000;
		outDegreeThreshold = 10;
		
		rand = new Random(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.unikoblenz.west.rdf.generator.RdfGenerator#generate()
	 */
	@Override
	public Graph generate() throws RdfGeneratorException {
		log.debug("generating graph");
		return convertVertexSetToGraph(generateVertexSet());
	}

	// add doc
	private List<Vertex> generateVertexSet()  throws RdfGeneratorException {
		ValueFactory fac = new ValueFactoryImpl();
		
		// create vertices
		List<Vertex> vertices = new ArrayList<Vertex>(numberOfVertices);
		 
		for(int i = 0; i < numberOfVertices; i++) {
			vertices.add(new Vertex(fac.createURI(namespace, String.valueOf(i))));
		}
		
		// create predicates (edge labels)
		URI label = fac.createURI(namespace, "label");
		
		// create connections
		HashMap<URI, Integer> overThreshold = new HashMap<URI, Integer>(numberOfVertices);
		while(overThreshold.keySet().size() < numberOfVertices) {
			Vertex v1 = vertices.get(rand.nextInt(numberOfVertices));
			Vertex v2 = vertices.get(rand.nextInt(numberOfVertices));
			
			try {
				v1.addEdge(label, v2);
			} catch (EdgeGenerationException e) {
				continue;
			}
			
			if(v1.getNeighbors().size() == outDegreeThreshold) {
				overThreshold.put(v1.getUri(), 1);
			}
		}
		
		return vertices;
	}
	
	// add doc
	private Graph convertVertexSetToGraph(List<Vertex> set) throws RdfGeneratorException {
		Graph graph = new TreeModel();
		
		for(Vertex v : set) {
			for(Edge e : v.getNeighbors()) {
				graph.add(v.getUri(), e.getLabel(), e.getTarget().getUri(), new Resource[0]);
			}
		}
		return graph;
	}
}
