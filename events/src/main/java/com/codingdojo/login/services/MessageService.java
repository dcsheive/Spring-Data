package com.codingdojo.login.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.login.models.Message;
import com.codingdojo.login.repositories.MessageRepository;

@Service
public class MessageService {
	private final MessageRepository mRepo;
	public MessageService(MessageRepository mRepo) {
		this.mRepo = mRepo;
	}
	public List<Message> allMessages(){
		return mRepo.findAll();
	}
	public Message createMessage(Message p) {
		return mRepo.save(p);
	}
}
