package diff.rest.proxy;

import java.util.ArrayList;
import java.util.List;

import diff.rest.DiffResponse;
import diff.rest.factory.RevisionFactory;

/**
 * Singleton Class that maintain the list of Documents and provide access to the 
 * revisions inside each documents. These methods are called by Proxy and implementation 
 * is encapsulated
 * 
 * @author Dias, Edison
 */
public class ControllerImpl{
	
	private static ControllerImpl uniqueInstance;
	private List<Document> documents = new ArrayList<Document>();

	/**
	 * This method returns existent instances or a new one when does not exists   
	 * 
	 * @return
	 */
	public static ControllerImpl getInstance()
	{
		if (uniqueInstance==null)
		{
			uniqueInstance = new ControllerImpl();	
		}
		return uniqueInstance;
	}
	
	/**
	 * This method iterates over the document list and if not exists then create a new one 
	 * and return the document object
	 * 
	 * @param id
	 * @return
	 */
	private Document findDocument(String id)
	{
		for(Document doc : documents)
		{
			if (doc.getId().equals(id)) return doc;
		}
		return null;
	}
	
	/**
	 * This method iterates over the document list and if not exists then create a new one 
	 * and return the document object
	 * 
	 * @param id
	 * @return
	 */
	private Document findOrCreateDocument(String id)
	{
		Document document = findDocument(id);
		if (document!=null) return document; else return createDocument(id);
	}
	
	/**
	 * Create a new Document object based on id
	 * 
	 * @param id
	 * @return
	 */
	private Document createDocument(String id){
		
		Document doc = new Document(id);
		documents.add(doc);
		return doc;
	}
	
	/**
	 * This method ensures document associated to Right revision uploaded
	 * 
	 * @param id
	 * @param right
	 * @return
	 */
	public boolean createDocumentAndRevisionRight(String id, String value)
	{
		Document doc = findOrCreateDocument(id);
		if (doc!=null && (value!=null && !value.isEmpty()))
		{
			doc.setRevisionRight(new RevisionFactory().createNewRevision(value));
			return true;
		}
		return false;
	}
	
	/**
	 * This method ensures document associated to Left revision uploaded
	 * 
	 * @param id
	 * @param left
	 * @return
	 */
	public boolean createDocumentAndRevisionLeft(String id, String value)
	{
		Document doc = findOrCreateDocument(id);
		if (doc!=null && (value!=null && !value.isEmpty()))
		{
			doc.setRevisionLeft(new RevisionFactory().createNewRevision(value));
			return true;
		}
		return false;
	}
	
	/**
	 * This method find a document based on id and call compare() to diff left|right revisions
	 * 
	 * @param id
	 * @return
	 */
	public DiffResponse viewDiff(String id){
		Document doc = findOrCreateDocument(id);
		return doc.compare();
	}
	
	/**
	 * This method find a document based on id and delete it from documents list 
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeDiff(String id){
		Document document = findDocument(id);
		if (document==null) return false; return getDocuments().remove(document);
	}
	
	/**
	 * This method return a list of documents
	 * @return
	 */
	public List<Document> getDocuments()
	{
		return documents;
	}
}
