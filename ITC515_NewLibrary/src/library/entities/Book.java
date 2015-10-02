package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

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
		
		if(loan == null)
			throw new IllegalArgumentException ("");
		if (state != EBookState.AVAILABLE)
			throw new RuntimeException ("");
		
			state = EBookState.ON_LOAN;
			this.loan = loan;
	}

	
	public EBookState isAvailable()
	{
		this.setState(isAvailable());
		return EBookState.AVAILABLE;
	}
	
	public ILoan getLoan(){	
		return (state == EBookState.ON_LOAN ? loan : null);
		
	}
	public void returnBook (boolean damaged){	
		if (state != EBookState.ON_LOAN)
			throw new RuntimeException ("");
		{
			state = (damaged) ? EBookState.DAMAGED : EBookState.AVAILABLE; loan = null;
			}
			
		}
	public void lose(){
		if (state == EBookState.ON_LOAN){
			this.state = EBookState.LOST;
		}
		else
			throw new RuntimeException ("");
		
	} 
	public void repair(){
		if (state == EBookState.DAMAGED){
			this.state = EBookState.AVAILABLE;
		}
		else
			throw new RuntimeException ("");
	}
	
	public void dispose(){
		if (state != EBookState.AVAILABLE || state != EBookState.DAMAGED || state != EBookState.LOST)
			throw new RuntimeException("");
		
			this.state = EBookState.DISPOSED;
			
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

	@Override
	public void setState(EBookState state) {
		this.state = state;
		
	}
	
	

	
		
	
	
	
	
	
	
	
}


