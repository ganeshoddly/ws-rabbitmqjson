package com.example.messaging;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TipsMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private final String text;
	private final int priority;
	private final boolean secret;

	public TipsMessage(@JsonProperty("text") final String text,
								@JsonProperty("priority") final int priority,
								@JsonProperty("secret") final boolean secret) {
		this.text = text;
		this.priority = priority;
		this.secret = secret;
	}

	public String getText() {
		return text;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "TipsMessage{" +
				"text='" + text + '\'' +
				", priority=" + priority +
				", secret=" + secret +
				'}';
	}
}