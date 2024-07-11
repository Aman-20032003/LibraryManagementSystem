package com.lms.repository.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
@Document(collection  = "Log_Activities")
@Data
@Builder
public class LogEntity {
	    private String message;
	    private String timestamp;
}
