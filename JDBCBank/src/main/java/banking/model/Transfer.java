package banking.model;

import java.time.LocalDateTime;

public class Transfer {
	private Integer transferID;
	private TransferType type;
	private Integer userID;
	private Integer toAccountID;
	private Integer fromAccountID;
	private Double amount;
	private LocalDateTime timestamp;
	
//---CONSTRUCTORS---//
	
	public Transfer(Integer transferID, TransferType type, Integer userID, Integer toAccountID, Integer fromAccountID, Double amount, LocalDateTime timestamp) {
		super();
		this.transferID = transferID;
		this.setType(type);
		this.userID = userID;
		this.toAccountID = toAccountID;
		this.fromAccountID = fromAccountID;
		this.amount = amount;
		this.setTimestamp(timestamp);
	}
	
//--FUNCTIONS--//
	
	//---GETTERS/SETTERS---//
	public Integer getTransferID() {
		return transferID;
	}
	public void setTransferID(Integer transferID) {
		this.transferID = transferID;
	}
	public TransferType getType() {
		return type;
	}

	public void setType(TransferType type) {
		this.type = type;
	}

	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getToAccountID() {
		return toAccountID;
	}
	public void setToAccountID(Integer toAccountID) {
		this.toAccountID = toAccountID;
	}
	public Integer getFromAccountID() {
		return fromAccountID;
	}
	public void setFromAccountID(Integer fromAccountID) {
		this.fromAccountID = fromAccountID;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
