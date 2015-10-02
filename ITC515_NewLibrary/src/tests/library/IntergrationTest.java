package tests.library;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import library.BorrowUC_CTL;
import library.daos.BookDAO;
import library.daos.BookHelper;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.entities.Loan;
import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;

public class IntergrationTest {
	
    BorrowUC_CTL ctl_;
    
    IScanner scanner;
    ICardReader reader;
    IPrinter printer;
    IDisplay display;
    IBorrowUI ui;
    
    IBookDAO bookDAO;
    IMemberDAO memberDAO;
    ILoanDAO loanDAO;
    
    Date date, dueDate;
    Calendar calender;



    @Before
    public void setUp() throws Exception {
    	scanner = mock(IScanner.class);
    	reader = mock(ICardReader.class);
        printer = mock(IPrinter.class);
        display = mock(IDisplay.class);
        ui = mock(IBorrowUI.class);
        
        bookDAO = new BookDAO(new BookHelper());
        memberDAO = new MemberMapDAO(new MemberHelper());
        loanDAO = new LoanMapDAO(new LoanHelper());
                
        ctl_ = new BorrowUC_CTL(reader, scanner, printer, display, bookDAO, loanDAO, memberDAO);
        
        IBook book = bookDAO.addBook("Bob Jones", "Subject", "123456");
        
        IMember member = memberDAO.addMember("Fn", "Ln", "Phone", "email");
        
        calender = Calendar.getInstance();
        date = new Date();
        calender.setTime(date);
        calender.add(Calendar.DATE, ILoan.LOAN_PERIOD);
        date = calender.getTime();

        
        ILoan loan = new Loan(book, member, date, dueDate);
        
        loanDAO.commitLoan(loan);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCardSwipedBorrowingEnabledNoFinesNoRestrictions() {
        
        ctl_.setState(EBorrowState.INITIALIZED);
        
        ctl_.cardSwiped(1);
        
        verify(reader).setEnabled(false);
        verify(scanner).setEnabled(true);
        verify(ui).setState(EBorrowState.SCANNING_BOOKS);
        verify(ui).displayMemberDetails(1, "Fn", "Ln");
        verify(ui).displayExistingLoan(any(String.class));    
        
        //assert
        assertEquals(EBorrowState.SCANNING_BOOKS, ctl_.getState());
        
    }


}