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
	private int id; 
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
		}
	public void lose(){
		
	}
	public void repair(){
	}
	
	public void dispose(){
	}
	public EBookState getState(){
	}
	public String getAuthor(){
	}
	public String getTitle(){
	}
	public String getCallNumber(){
	}
	public int getID(){
		
	}
	
		
	
	
	
	
	
	
	
}


