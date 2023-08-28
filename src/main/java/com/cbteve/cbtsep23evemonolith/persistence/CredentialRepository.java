package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credential, String> {
}