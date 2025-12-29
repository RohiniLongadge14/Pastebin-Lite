package io.reflectoring.pastebinlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.reflectoring.pastebinlite.entity.Paste;

public interface PasteRepository extends JpaRepository<Paste, String> {
}
