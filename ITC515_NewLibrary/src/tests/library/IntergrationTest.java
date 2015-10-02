package tests.library;

import org.junit.Test;

import library.BorrowUC_UI;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;

public class IntergrationTest {
	
	private IBookDAO bookDAO;
	private ILoanDAO loanDAO; 
	private IMemberDAO memeberDAO; 
	
	private ICardReader cardReader;
	private IPrinter printer; 
	private IScanner scanner; 
	private IDisplay display; 
	private BorrowUC_UI ui; 
	
	public void set() {
		
	}
	
	public void Initialize(){
		
		
	}
	
	@Test 
	public void cardSwipe1(){
		
	}
	@Test 
	public void cardSwipe2(){
		
	}
	@Test 
	public void cardSwipe3(){
	
	}
	@Test 
	public void cardSwipe4(){
	
	}
	public void cardSwipe5(){
	
	}
	
	

}
