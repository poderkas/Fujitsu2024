package com.poderkas.fujitsu2024.Exceptions;

public class DuplicateDeliveryException extends RuntimeException{
    public DuplicateDeliveryException(String message) {
        super(message);
    }
}
