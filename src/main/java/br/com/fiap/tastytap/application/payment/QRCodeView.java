package br.com.fiap.tastytap.application.payment;

public record QRCodeView(Long transactionId, String qrCodeUrl) {}
