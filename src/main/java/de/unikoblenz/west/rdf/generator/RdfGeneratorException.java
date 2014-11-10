package de.unikoblenz.west.rdf.generator;

/**
 * indicates a malfunction of an RdfGenerator
 * @author lkastler
 */
public class RdfGeneratorException extends Exception {

	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * creates an RdfGeneratorException with given message and cause.
	 * @param message - message of exception.
	 * @param cause - cause of exception.
	 */
	public RdfGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * creates an RdfGeneratorException with given message.
	 * @param message - message of exception.
	 */
	public RdfGeneratorException(String message) {
		super(message);
	}

	
	
}
