package com.machine.coffeemachine.repo;

import com.machine.coffeemachine.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
