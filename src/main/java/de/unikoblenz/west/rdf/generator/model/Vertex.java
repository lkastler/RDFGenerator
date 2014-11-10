package de.unikoblenz.west.rdf.generator.model;

import java.util.LinkedList;
import java.util.List;

import org.openrdf.model.URI;

import de.unikoblenz.west.rdf.generator.EdgeGenerationException;

/**
 * graph vertex for graph generation
 * @author lkastler
 */
public class Vertex {

	private final URI uri;
	private final List<Edge> neighbors = new LinkedList<Edge>();

	// TODO add doc
	public Vertex(URI uri) {
		this.uri = uri;
	}

	public void addEdge(URI edgeLabel, Vertex vertex) throws EdgeGenerationException {
		if(this.equals(vertex)) {
			throw new EdgeGenerationException("do not want to connect vertex with itself");
		}
		Edge e = new Edge(edgeLabel, vertex);
		
		if(!neighbors.contains(e)) {
			neighbors.add(e);
		}
		else {
			throw new EdgeGenerationException("edge " + e + " already exists");
		}
		
	}
	
	/**
	 * @return the URI of this Vertex.
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * @return the neighbors
	 */
	public List<Edge> getNeighbors() {
		return neighbors;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vertex [uri=" + uri + ", neighbors=" + neighbors.size() + "]";
	}
	
	
}
