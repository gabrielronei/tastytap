package br.com.fiap.tastytap.insfraestructure.payment;

public record QRCodeResponse(Long transactionId, String qrCodeUrl) {}
