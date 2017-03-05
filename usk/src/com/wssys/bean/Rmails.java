package com.wssys.bean;

import com.wssys.entity.Mailclient;
import com.wssys.entity.Receivingmail;

public class Rmails {
	private Mailclient mc;
	private Receivingmail rm;

	public Mailclient getMc() {
		return mc;
	}

	public void setMc(Mailclient mc) {
		this.mc = mc;
	}

	public Receivingmail getRm() {
		return rm;
	}

	public void setRm(Receivingmail rm) {
		this.rm = rm;
	}
}
