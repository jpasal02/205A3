package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.EMemberState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Book implements IBook{

	private String author; 
	private String title; 
	private String callNumber;  
	private int bookID; 
	private ILoan loan; 
	private EBookState state; 

	
	
	public Book (String author, String title, String callNumber, int bookID) {
		
		if (author == null || author.isEmpty())
			throw new IllegalArgumentException ("Error: Author");
		if (title == null || title.isEmpty())
			throw new IllegalArgumentException ("Error: Title");
		if (callNumber == null || callNumber.isEmpty())
			throw new IllegalArgumentException ("Error: Call Number");
		if(bookID <= 0)
			throw new IllegalArgumentException ("Error: Book ID");
		
		this.author = author;
		this.title = title; 
		this.callNumber = callNumber;
		this.bookID = bookID;
	}
	
	public void borrow(ILoan loan){
		
		if (state == EBookState.AVAILABLE){
			state = EBookState.ON_LOAN;
			this.loan = loan;
	}
	else
			throw new RuntimeException ("");
	}
	
	public ILoan getLoan(){	
		return (state == EBookState.ON_LOAN ? loan : null);
		
	}
	public void returnBook (boolean damaged){	
		if (state == EBookState.ON_LOAN){
			state = (damaged) ? EBookState.DAMAGED : EBookState.AVAILABLE; loan = null;
			}
		else
			throw new RuntimeException ("");
		}
	public void lose(){
		if (state == EBookState.ON_LOAN){
			state = EBookState.LOST;
		}
		else
			throw new RuntimeException ("");
		
	} 
	public void repair(){
		if (state == EBookState.DAMAGED){
			state = EBookState.AVAILABLE;
		}
		else
			throw new RuntimeException ("");
	}
	
	public void dispose(){
		if (state == EBookState.AVAILABLE || state == EBookState.DAMAGED || state == EBookState.LOST)
			state = EBookState.DISPOSED;
		else
			throw new RuntimeException("");
	}
	public EBookState getState(){
		return state; 
	}
	public String getAuthor(){
		return author; 
	}
	public String getTitle(){
		return title; 
	}
	public String getCallNumber(){
		return callNumber;
	}
	public int getID(){
		return bookID; 
		
		
	}

	
		
	
	
	
	
	
	
	
}


