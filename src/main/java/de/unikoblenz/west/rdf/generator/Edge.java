package de.unikoblenz.west.rdf.generator;

import org.openrdf.model.URI;

/**
 * TODO add doc
 * @author lkastler
 */
public class Edge {

	private final URI label;
	private final Vertex target;
	
	/**
	 * @param label
	 * @param target
	 */
	public Edge(URI label, Vertex target) {
		super();
		this.label = label;
		this.target = target;
	}
	
	/**
	 * @return the label
	 */
	public URI getLabel() {
		return label;
	}
	/**
	 * @return the target
	 */
	public Vertex getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Edge other = (Edge) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [label=" + label + ", target=" + target + "]";
	}
}
