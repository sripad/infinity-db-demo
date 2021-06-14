package com.example.demo.model;

import java.util.List;

import com.example.demo.model.event.Event;

public class Transaction extends InfinityDBModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Event inboundEvent;

	List<Event> outboundEvents;

	String status;
}
