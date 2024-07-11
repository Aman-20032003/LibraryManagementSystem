package com.lms.controller.request;



import java.util.List;

import lombok.Data;

@Data
public class BorrowBookRequest {
private String email;
private List<Integer>serialNo;

}
