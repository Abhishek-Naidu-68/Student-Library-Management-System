package com.avenger.StudentLibraryManagement.Repositories;

import com.avenger.StudentLibraryManagement.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
