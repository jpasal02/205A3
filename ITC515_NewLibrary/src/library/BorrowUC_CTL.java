package library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.IBorrowUIListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.ICardReaderListener;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.interfaces.hardware.IScannerListener;

public class BorrowUC_CTL implements ICardReaderListener, 
									 IScannerListener, 
									 IBorrowUIListener {
	
	private ICardReader reader;
	private IScanner scanner; 
	private IPrinter printer; 
	private IDisplay display;
	//private String state;
	private int scanCount = 0;
	private IBorrowUI ui;
	private EBorrowState state; 
	private IBookDAO bookDAO;
	private IMemberDAO memberDAO;
	private ILoanDAO loanDAO;
	
	private List<IBook> bookList;
	private List<ILoan> loanList;
	private IMember borrower;
	
	private JPanel previous;


	public BorrowUC_CTL(ICardReader reader, IScanner scanner, 
			IPrinter printer, IDisplay display,
			IBookDAO bookDAO, ILoanDAO loanDAO, IMemberDAO memberDAO ) {

		this.display = display;
		this.ui = new BorrowUC_UI(this);
		state = EBorrowState.CREATED;
	}
	
	public void initialise() {
		previous = display.getDisplay();
		display.setDisplay((JPanel) ui, "Borrow UI");	
		
	}
	
	public void close() {
		display.setDisplay(previous, "Main Menu");
	}

	@Override
	public void cardSwiped(int memberID) {
		
		state.equals(EBorrowState.INITIALIZED); 
		borrower = memberDAO.getMemberByID(memberID);
		
		if (borrower == null){
			ui.displayErrorMessage("field must be a postivie int");
			return;
		}
		if (borrower.hasOverDueLoans()) {
			state.equals(EBorrowState.BORROWING_RESTRICTED);
			ui.displayErrorMessage("Memeber is restricted");
		}
		else{
			state.equals(EBorrowState.SCANNING_BOOKS);
		}
		
		
		
		throw new RuntimeException("Not implemented yet");
	}
	
	
	
	@Override
	public void bookScanned(int barcode) {
		
	state.equals(EBorrowState.SCANNING_BOOKS);
		
	}

	
	private void setState(EBorrowState state) {
		this.state = state;
		
		if (state == EBorrowState.INITIALIZED){
			reader.setEnabled(true);
			scanner.setEnabled(false);
		}
		if (state == EBorrowState.SCANNING_BOOKS){
			reader.setEnabled(false);
			scanner.setEnabled(true);
		}
		if (state == EBorrowState.BORROWING_RESTRICTED || state == state.CONFIRMING_LOANS){
			reader.setEnabled(false);
			scanner.setEnabled(false);
		}
		else {
			reader.setEnabled(true);
			scanner.setEnabled(true);
			
			
		}
		
	}

	@Override
	public void cancelled() {
		close();
	}
	
	@Override
	public void scansCompleted() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansConfirmed() {
		while (state == EBorrowState.CONFIRMING_LOANS && loanList.size() > 0 && loanList.size() < 5){
			
		}
	}

	@Override
	public void loansRejected() {
		while (state == EBorrowState.CONFIRMING_LOANS && loanList.size() > 0){
		
		}
	}

	private String buildLoanListDisplay(List<ILoan> loans) {
		StringBuilder bld = new StringBuilder();
		for (ILoan ln : loans) {
			if (bld.length() > 0) bld.append("\n\n");
			bld.append(ln.toString());
		}
		return bld.toString();		
	}

}
