@FirstApplicationFeature
Feature: Should test CPF

Scenario: an cpf
    Given A new cpf = "80707309000"
    When create the object
    Then should create the cpf correctly