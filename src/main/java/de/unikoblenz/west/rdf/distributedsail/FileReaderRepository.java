package de.unikoblenz.west.rdf.distributedsail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.unikoblenz.west.rdf.distributedsail.DistributedRepository;

public class FileReaderRepository implements Runnable {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final DistributedRepository repo;
	private String fileName = null;
	private Thread t = null;
	
	public FileReaderRepository(DistributedRepository repo) {
		this.repo = repo;		
	}

	/**
	 * reads a file of RDF triples and push them into the DistributedSail stores via a DistributedRepository.
	 * 
	 * @param fileName - name of the file.
	 * @throws RepositoryException
	 */
	public void execute(String fileName) throws RepositoryException {
		if(t != null) {
			// TODO not a good idea to use RepositoryException!
			throw new RepositoryException("could not start");
		}
		this.fileName = fileName;
		
		t = new Thread(this);
		t.start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO use an RDF reader here maybe
		try {
			RepositoryConnection con = repo.getConnection();
			ValueFactory fac = con.getValueFactory();
			
			BufferedReader read = new BufferedReader(new FileReader(new File(fileName)));
			String s;
			while(!Thread.currentThread().isInterrupted() && (s = read.readLine()) != null) {
				String line = s.split(".")[0];
				String[] token = line.split(" ");
				
				Resource sub = fac.createURI(token[0].trim());
				
				URI pred = fac.createURI(token[1].trim());
				
				Value obj = fac.createURI(token[2].trim());
				
				con.add(sub, pred, obj, new Resource[0]);
			}
			
			read.close();
		} 
		catch (FileNotFoundException e) {
			log.error("could not open file: " + fileName, e);
		} catch (IOException e) {
			log.error("could not read file properly: " + fileName, e);
		} catch (RepositoryException e) {
			log.error("could not open repo connection", e);
		}
		finally {
			t = null;
		}
	}
}
