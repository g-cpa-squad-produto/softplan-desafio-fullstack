package br.com.softplan.process.api.dto;

import java.io.Serializable;

public class Summary implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer amountPending;
	private Integer amountAnalyzing;
	private Integer amountCanceled;
	private Integer amountClosed;
	
	public Integer getAmountPending() {
		return amountPending;
	}
	public void setAmountPending(Integer amountPending) {
		this.amountPending = amountPending;
	}
	public Integer getAmountAnalyzing() {
		return amountAnalyzing;
	}
	public void setAmountAnalyzing(Integer amountAnalyzing) {
		this.amountAnalyzing = amountAnalyzing;
	}
	public Integer getAmountCanceled() {
		return amountCanceled;
	}
	public void setAmountCanceled(Integer amountCanceled) {
		this.amountCanceled = amountCanceled;
	}
	public Integer getAmountClosed() {
		return amountClosed;
	}
	public void setAmountClosed(Integer amountClosed) {
		this.amountClosed = amountClosed;
	}	
}
