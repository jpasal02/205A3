package library.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookDAO implements IBookDAO{
	
	private IBookHelper helper; 
	private List<IBook> map;
	private int nextID;
	
	
	public BookDAO (IBookHelper helper){
		if (helper == null)
			throw new IllegalArgumentException ("Error: Helper");
	
		
	}
	
	public IBook addBook(String author, String Title, String callNumber){
		IBook book = helper.makeBook(author, Title, callNumber, nextID++);
		map.add(book);
		return book;
		
		
	}
	
	public IBook getBookByID(int id){
		
	}
	
	public List<IBook> listBooks(){
		
	}
	
	public List<IBook> findBooksByAuthor(String author){
		
	}
	
	public List<IBook> findBooksByTitle(String title){
		
	}
	public List<IBook> findBooksByAuthorTitle(String author, String title){
		
	}

}
